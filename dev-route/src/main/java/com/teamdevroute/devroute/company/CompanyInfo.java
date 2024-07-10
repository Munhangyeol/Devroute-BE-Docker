package com.teamdevroute.devroute.company;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Getter
@Table(name = "company_info")
public class CompanyInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "grade")
    private double grade;

    @Column(name = "average_salary")
    private int averageSalary;

    @Column(name = "recruit_count")
    private Long recruitCount;

    @Column(name = "info", columnDefinition = "text")
    private String info;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id")
    private Company company;

    @Builder
    public CompanyInfo(double grade, int averageSalary, String info, Company company) {
        this.grade = grade;
        this.averageSalary = averageSalary;
        this.recruitCount = 0L;
        this.info = info;
        this.company = company;
    }
}
