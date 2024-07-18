package com.teamdevroute.devroute.crawling;

import com.teamdevroute.devroute.company.Company;
import com.teamdevroute.devroute.company.CompanyRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyCrawlingService {

    private CompanyRepository companyRepository;

    public CompanyCrawlingService(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    public void createCompany(
            List<String> enterpriseNames,
            List<String> enterpriseSalaries,
            List<String> enterpriseGrades,
            List<String> enterpriseLogos
    ) {
        for(int i=0;i<enterpriseNames.size();i++) {
            companyRepository.save(
                    Company.builder()
                    .name(enterpriseNames.get(i))
                    .averageSalary(enterpriseSalaries.get(i))
                    .grade(Double.parseDouble(enterpriseGrades.get(i)))
                    .logoUrl(enterpriseLogos.get(i))
                    .build()
            );
        }
    }
}
