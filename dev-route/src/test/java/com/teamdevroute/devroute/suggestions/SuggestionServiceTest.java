package com.teamdevroute.devroute.suggestions;

import com.teamdevroute.devroute.api.suggestion.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class SuggestionServiceTest {

    @Autowired
    private SuggestionService suggestionService;

    @Autowired
    private SuggestionRepository suggestionRepository;

    @DisplayName("건의사항을 업로드합니다.")
    @Test
    void upload_suggestion() {
        SuggestionCreateRequest suggestions1 = SuggestionCreateRequest.builder()
                .content("건의사항입낟~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~")
                .suggestionCategory(SuggestionCategory.REPORT_SUGGEST)
                .build();

        SuggestionCreateRequest suggestions2 = SuggestionCreateRequest.builder()
                .content("건의사항입낟~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~32323232")
                .suggestionCategory(SuggestionCategory.REPORT_ERROR)
                .build();

        suggestionService.createSuggestion(suggestions1);
        suggestionService.createSuggestion(suggestions2);

        Long cnt = suggestionRepository.count();
        assertThat(cnt).isEqualTo(2);
    }
}
