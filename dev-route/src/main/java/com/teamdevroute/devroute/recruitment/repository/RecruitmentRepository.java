package com.teamdevroute.devroute.recruitment.repository;

import com.teamdevroute.devroute.recruitment.domain.Recruitment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecruitmentRepository extends JpaRepository<Recruitment, Long> {
    boolean existsByUrl(String url);
}
