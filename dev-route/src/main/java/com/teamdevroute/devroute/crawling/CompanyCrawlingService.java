package com.teamdevroute.devroute.crawling;

import com.teamdevroute.devroute.company.Company;
import com.teamdevroute.devroute.company.CompanyRepository;
import com.teamdevroute.devroute.crawling.dto.CrawledCompanyDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyCrawlingService {

    private CompanyRepository companyRepository;

    public CompanyCrawlingService(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    public void createCompany(CrawledCompanyDto crawledCompanyDto) {
        List<String> enterpriseNames = crawledCompanyDto.getEnterpriseNames();
        List<String> enterpriseSalaries = crawledCompanyDto.getEnterpriseSalaries();
        List<String> enterpriseGrades = crawledCompanyDto.getEnterpriseGrades();
        List<String> enterpriseLogo = crawledCompanyDto.getEnterpriseLogo();

        for(int i=0;i<enterpriseNames.size();i++) {
            companyRepository.save(
                    Company.builder()
                    .name(enterpriseNames.get(i))
                    .averageSalary(enterpriseSalaries.get(i))
                    .grade(Double.parseDouble(enterpriseGrades.get(i)))
                    .logoUrl(enterpriseLogo.get(i))
                    .build()
            );
        }
    }
}
