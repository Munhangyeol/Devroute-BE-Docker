package com.teamdevroute.devroute.bookmark;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Getter
public class BookmarkUpdateRequest {
    private Long userId;
    private Long id;
    private String type;
    public BookmarkUpdateRequest() {
    }
}
