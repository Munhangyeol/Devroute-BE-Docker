package com.teamdevroute.devroute.api.visitorcount;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.Optional;

public interface VisitorCountRepository extends JpaRepository<VisitorCount, Long> {

    Optional<VisitorCount> findByVisitDate(LocalDate date);

    @Query("SELECT sum(v.visitCount) FROM VisitorCount v " +
            "WHERE v.visitDate BETWEEN :start AND :end")
    Long findByVisitDateBetween(LocalDate start, LocalDate end);
}
