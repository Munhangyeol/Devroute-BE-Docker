package com.teamdevroute.devroute.video.dto.udemy;

import com.teamdevroute.devroute.video.domain.Videos;
import lombok.Builder;

public record UdemyVideoDTO(String url, String title, String thumbnailUrl,Long price) {

    @Builder
    public UdemyVideoDTO(String url, String title, String thumbnailUrl,Long price){
        this.url = url;
        this.title = title;
        this.thumbnailUrl = thumbnailUrl;
        this.price=price;
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
                .price(Long.valueOf(price))
                .build();
    }
}