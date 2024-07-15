package com.teamdevroute.devroute.company;

import com.teamdevroute.devroute.global.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Getter
@Table(name = "company")
public class Company extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "logo_url", nullable = false)
    private String logoUrl;

    @Column(name = "click_count")
    private Long clickCount;

    @Column(name = "grade")
    private double grade;

    @Column(name = "average_salary")
    private int averageSalary;

    @Column(name = "recruit_count")
    private Long recruitCount;

    @Column(name = "info", columnDefinition = "text")
    private String info;

    @Builder
    public Company(String name, String logoUrl, Long clickCount, double grade, int averageSalary, Long recruitCount, String info) {
        this.name = name;
        this.logoUrl = logoUrl;
        this.clickCount = clickCount;
        this.grade = grade;
        this.averageSalary = averageSalary;
        this.recruitCount = recruitCount;
        this.info = info;
    }
}
