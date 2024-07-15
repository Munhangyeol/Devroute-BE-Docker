package com.teamdevroute.devroute.video.dto.youtube;

import com.teamdevroute.devroute.video.domain.Videos;
import lombok.Builder;

public record YoutubeVideoDTO(String url, String title, String thumbnailUrl) {

    @Builder
    public YoutubeVideoDTO(String url,String title,String thumbnailUrl){
        this.url = url;
        this.title = title;
        this.thumbnailUrl = thumbnailUrl;
    }
    public Videos toEntity(String platform_name,String teck_stack,Long count,Long rank){
        return Videos.builder()
                .url(url)
                .title(title)
                .thumnail_url(thumbnailUrl)
                .platform_name(platform_name)
                .teck_stack(teck_stack)
                .count(count)
                .rank(rank)
                .build();
    }
}