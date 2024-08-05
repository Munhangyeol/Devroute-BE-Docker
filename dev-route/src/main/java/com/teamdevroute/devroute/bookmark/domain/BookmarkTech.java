package com.teamdevroute.devroute.bookmark.domain;

import com.teamdevroute.devroute.video.domain.TechnologyStack;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookmarkTech {
    private Long id;
    private String name;

    public static BookmarkTech from(TechnologyStack technologyStack) {
        return BookmarkTech.builder()
                .id(technologyStack.getId())
                .name(technologyStack.getName())
                .build();
    }
}
