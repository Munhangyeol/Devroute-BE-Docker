package com.teamdevroute.devroute.api.suggestion;

import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
public class SuggestionCreateRequest {

    private SuggestionCategory suggestionCategory;
    private String content;

    @Builder
    public SuggestionCreateRequest(SuggestionCategory suggestionCategory, String content) {
        this.suggestionCategory = suggestionCategory;
        this.content = content;
    }

    public Suggestions toEntity() {
        return Suggestions.builder()
                .status(SuggestionStatus.IN_PROGRESS)
                .content(content)
                .suggestionCategory(suggestionCategory)
                .build();
    }
}
