package com.teamdevroute.devroute.roadmap.repository;

import com.teamdevroute.devroute.roadmap.domain.RoadmapStep;
import com.teamdevroute.devroute.roadmap.domain.RoadmapStepInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoadmapStepInfoRepository extends JpaRepository<RoadmapStepInfo,Long> {
    Optional<RoadmapStepInfo> findByRoadmapStep(RoadmapStep roadmapStep);
}
