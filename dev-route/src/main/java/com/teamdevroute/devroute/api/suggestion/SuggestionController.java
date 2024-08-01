package com.teamdevroute.devroute.api.suggestion;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@AllArgsConstructor
@Controller
public class SuggestionController {

    private SuggestionService suggestionService;

    @PostMapping("/error-page")
    public ResponseEntity uploadSuggestion(@RequestBody SuggestionCreateRequest request) {
        suggestionService.createSuggestion(request);

        return ResponseEntity.ok().body("업로드가 완료되었습니다.");
    }
}
