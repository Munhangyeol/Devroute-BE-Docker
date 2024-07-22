package com.teamdevroute.devroute.video.dto;

import lombok.Builder;

public record LectureResponseDTO(String url, String title, String thumnail_url,
                                 Long price, String platform_name) {

    public LectureResponseDTO(String url, String title, String thumnail_url,
                              Long price, String platform_name){
        this.url = url;
        this.title=title;
        this.thumnail_url = thumnail_url;
        this.price = price;
        this.platform_name = platform_name;

    }

}
