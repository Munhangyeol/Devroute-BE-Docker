package com.teamdevroute.devroute.roadmap.repository;

import com.teamdevroute.devroute.roadmap.domain.RoadmapStep;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoadmapStepRepository extends JpaRepository<RoadmapStep,Long> {
    Optional<RoadmapStep> findByDevelopmentField(String devlopmentField);
}
