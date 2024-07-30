package com.teamdevroute.devroute.api.visitorcount;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Entity
@Getter
public class VisitorCount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "visit_count")
    private Long visitCount;

    @Column(name = "visit_date", unique = true)
    private LocalDate visitDate;

    @Builder
    public VisitorCount(Long id, Long visitCount, LocalDate visitDate) {
        this.id = id;
        this.visitCount = visitCount;
        this.visitDate = visitDate;
    }

    public VisitorCount() {

    }

    public void updateVisitorCount() {
        this.visitCount++;
    }
}
