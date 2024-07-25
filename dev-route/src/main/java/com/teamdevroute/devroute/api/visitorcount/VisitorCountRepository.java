package com.teamdevroute.devroute.api.visitorcount;

import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.Optional;

public interface VisitorCountRepository extends JpaRepository<VisitorCount, Long> {

    VisitorCount findByVisitDate(LocalDate date);
}
