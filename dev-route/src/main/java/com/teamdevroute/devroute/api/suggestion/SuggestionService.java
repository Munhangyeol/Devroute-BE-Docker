package com.teamdevroute.devroute.api.suggestion;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@AllArgsConstructor
public class SuggestionService {

    private SuggestionRepository suggestionRepository;

    public void createSuggestion(SuggestionCreateRequest request) {
        if(request.getContent().isBlank()) {
            throw new IllegalArgumentException("내용을 입력해야 합니다.");
        }
        suggestionRepository.save(request.toEntity());
    }
}
