package com.teamdevroute.devroute.recruitment.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.teamdevroute.devroute.company.Company;
import com.teamdevroute.devroute.recruitment.domain.Recruitment;
import com.teamdevroute.devroute.recruitment.enums.SearchKeyWord;
import com.teamdevroute.devroute.recruitment.enums.Source;
import com.teamdevroute.devroute.recruitment.repository.CompanyRepository;
import com.teamdevroute.devroute.recruitment.repository.RecruitmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RecruitmentUpdateService {

    @Value("${saramin.access-key}")
    private static String accessKey;
    private static final String API_URL = "https://oapi.saramin.co.kr/job-search?access-key=" + accessKey + " &keywords=";

    private final RecruitmentRepository recruitmentRepository;
    private final CompanyRepository companyRepository;

    @Scheduled(cron = "0 0 0 * * ?") // 매일 자정 업데이트
    public void fetchJobs() {
        for (SearchKeyWord keyword : SearchKeyWord.values()) {
            fetchAndSaveJobs(keyword.name());
        }
    }

    private void fetchAndSaveJobs(String keyword) {

        RestTemplate restTemplate = new RestTemplate();
        ObjectMapper objectMapper = new ObjectMapper();

        String initialUrl = API_URL + keyword + "&start=0&count=100";
        String initialResponse = restTemplate.getForObject(initialUrl, String.class);

        try {
            int total = objectMapper.readTree(initialResponse).path("jobs").path("total").asInt();

            for (int i = 0; i <= total / 100; i++) {
                String url = API_URL + keyword + "&start=" + i + "&count=100";
                String response = restTemplate.getForObject(url, String.class);
                JsonNode saraminResponses = objectMapper.readTree(response).path("jobs").path("job");

                parseAndStoreRecruitment(saraminResponses);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void parseAndStoreRecruitment(JsonNode saraminResponses) {
        List<Recruitment> recruitments = new ArrayList<>();

        for (JsonNode saraminResponse : saraminResponses) {
            Recruitment recruitment = parseResponse(saraminResponse);
            if (!recruitmentRepository.existsByUrl(recruitment.getUrl())) {
                recruitments.add(recruitment);
            }
        }

        recruitmentRepository.saveAll(recruitments);
    }

    private Recruitment parseResponse(JsonNode jobNode) {
        String url = jobNode.path("url").asText();
        String companyName = jobNode.path("company").path("detail").path("name").asText();
        String experienceLevel = jobNode.path("position").path("experience-level").path("name").asText();
        long expirationTimestamp = jobNode.path("expiration-timestamp").asLong();
        LocalDateTime expirationDate = LocalDateTime.ofInstant(Instant.ofEpochSecond(expirationTimestamp), ZoneOffset.UTC);
        String techStacks = jobNode.path("position").path("job-code").path("name").asText();

        Company company = companyRepository.findByName(companyName).orElseGet(() -> {
            Company newCompany = new Company(companyName, null, 0L);
            return companyRepository.save(newCompany);
        });

        return Recruitment.builder()
                .techStacks(List.of(techStacks.split(",")))
                .annual(experienceLevel)
                .dueDate(expirationDate)
                .url(url)
                .company(company)
                .source(Source.SARAMIN)
                .build();
    }
}
