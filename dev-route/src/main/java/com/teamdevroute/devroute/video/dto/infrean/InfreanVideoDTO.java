package com.teamdevroute.devroute.video.dto.infrean;

import com.teamdevroute.devroute.video.domain.Videos;
import lombok.Builder;

public record InfreanVideoDTO(String url, String title, String thumbnailUrl,Long price) {
    @Builder
    public InfreanVideoDTO(String url, String title, String thumbnailUrl,Long price){
        this.url = url;
        this.title = title;
        this.thumbnailUrl = thumbnailUrl;
        this.price=price;
    }
    public Videos toEntity(String platform_name, String teck_stack, Long count, Long rank){
        return Videos.builder()
                .url(url)
                .title(title)
                .thumnail_url(thumbnailUrl)
                .platform_name(platform_name)
                .teck_stack(teck_stack)
                .count(count)
                .rank(rank)
                .price(Long.valueOf(price))
                .build();
    }
}
