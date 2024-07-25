package com.teamdevroute.devroute.video.Repository;

import com.teamdevroute.devroute.video.domain.TechnologyStack;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TechnologyStackRepository extends JpaRepository<TechnologyStack,Long> {

    long count();

    Optional<TechnologyStack> findByName(String name);
}
