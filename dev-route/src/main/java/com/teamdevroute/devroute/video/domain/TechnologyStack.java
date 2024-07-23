package com.teamdevroute.devroute.video.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class TechnologyStack {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "technology_stack_id")
    private Long id;
    private String name;
    private Long count;

    @Builder
    public TechnologyStack(String name, Long count) {
        this.name = name;
        this.count = count;
    }

    public TechnologyStack() {
    }

    public void setAddedCount() {
        this.count = this.count + 1;
    }
}