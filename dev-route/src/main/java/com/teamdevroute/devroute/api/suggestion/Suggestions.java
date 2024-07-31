package com.teamdevroute.devroute.api.suggestion;

import com.teamdevroute.devroute.global.BaseTimeEntity;
import com.teamdevroute.devroute.user.domain.User;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
public class Suggestions extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private SuggestionCategory suggestionCategory;
    private String content;
    @Column
    @Enumerated(EnumType.STRING)
    private SuggestionStatus status;
    @ManyToOne
    private User user;

    @Builder
    public Suggestions(Long id, SuggestionCategory suggestionCategory, String content, SuggestionStatus status, User user) {
        this.id = id;
        this.suggestionCategory = suggestionCategory;
        this.content = content;
        this.status = status;
        this.user = user;
    }
}
