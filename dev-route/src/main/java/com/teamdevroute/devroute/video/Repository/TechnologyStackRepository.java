package com.teamdevroute.devroute.video.Repository;

import com.teamdevroute.devroute.video.domain.TechnologyStack;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TechnologyStackRepository extends JpaRepository<TechnologyStack,Long> {

    long count();

    TechnologyStack findByName(String name);
}
