package com.teamdevroute.devroute.company.controller;

import com.teamdevroute.devroute.company.dto.CompanyResponse;
import com.teamdevroute.devroute.company.service.CompanyService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@AllArgsConstructor
@Slf4j
@Controller
public class CompanyController {

    private CompanyService companyService;

    @GetMapping("/recruit/enterprise")
    public ResponseEntity getAllCompanies() {
        List<CompanyResponse> response = companyService.findAll();
        return ResponseEntity.ok(response);
    }
}
