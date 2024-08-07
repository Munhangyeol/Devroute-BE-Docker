package com.teamdevroute.devroute.recruitment.repository;

import com.teamdevroute.devroute.recruitment.domain.Recruitment;
import com.teamdevroute.devroute.user.enums.DevelopField;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecruitmentRepository extends JpaRepository<Recruitment, Long> {
    boolean existsByUrl(String url);

    @Query("select r from Recruitment r where r.developField = :developField")
    List<Recruitment> findByDevelopField(DevelopField developField);

    @Query("SELECT r FROM Recruitment r WHERE r.developField = :developField AND r.source = 'SARAMIN'")
    List<Recruitment> findByDevelopFieldAndSource(@Param("developField") DevelopField developField);
}
