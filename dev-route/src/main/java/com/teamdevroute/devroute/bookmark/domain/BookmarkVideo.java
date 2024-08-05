package com.teamdevroute.devroute.bookmark.domain;

import com.teamdevroute.devroute.video.domain.Videos;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookmarkVideo {
    private Long id;
    private String url;
    private String title;
    private String thumnail_url;
    private Long price;

    public static BookmarkVideo from(Videos videos) {
        return BookmarkVideo.builder()
                .id(videos.getId())
                .url(videos.getUrl())
                .title(videos.getTitle())
                .thumnail_url(videos.getThumnail_url())
                .price(videos.getPrice())
                .build();
    }
}
