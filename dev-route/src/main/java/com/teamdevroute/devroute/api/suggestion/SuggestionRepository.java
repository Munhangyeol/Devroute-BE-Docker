package com.teamdevroute.devroute.api.suggestion;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SuggestionRepository extends JpaRepository<Suggestions, Long> {

    long count();

}
