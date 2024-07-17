package com.teamdevroute.devroute.video.fetcher;

import com.teamdevroute.devroute.video.dto.udemy.UdemyApiResponse;
import com.teamdevroute.devroute.video.enums.TechnologyStackName;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

import static com.teamdevroute.devroute.video.constans.ApiConstans.QUERY_UDEMY_SET_FEILD;
import static com.teamdevroute.devroute.video.constans.ApiConstans.UDEMY_API_URL_SEARCH;

@Component
public class UdemyVideoFetcher {
    private final RestTemplate restTemplate;
    @Value("${udemy.api.clientId}")
    private String udemyApiClientId;
    @Value("${udemy.api.key}")
    private String udemyApiKey;

    public UdemyVideoFetcher(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public UdemyApiResponse fetchUdemyVideos(TechnologyStackName value) {
        HttpHeaders headers = new HttpHeaders();
        setHeaderAuthBeforeFetchUdemyApi(headers);
        HttpEntity<String> entity = new HttpEntity<>(headers);
        return restTemplate.exchange(getUdemyApiUrl(value), HttpMethod.GET, entity, UdemyApiResponse.class).getBody();
    }

    private void setHeaderAuthBeforeFetchUdemyApi(HttpHeaders headers) {
        String auth = udemyApiClientId + ":" + udemyApiKey;
        String encodedAuth = Base64.getEncoder().encodeToString(auth.getBytes(StandardCharsets.US_ASCII));
        headers.set("Authorization", "Basic " + encodedAuth);
    }

    private String getUdemyApiUrl(TechnologyStackName value) {
        return UDEMY_API_URL_SEARCH + value + QUERY_UDEMY_SET_FEILD;
    }
}