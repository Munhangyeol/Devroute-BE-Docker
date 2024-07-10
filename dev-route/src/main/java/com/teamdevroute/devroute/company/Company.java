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

    @Builder
    public Company(String name, String logoUrl) {
        this.name = name;
        this.logoUrl = logoUrl;
        this.clickCount = 0L;
    }
}
