package com.teamdevroute.devroute.video.domain;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Converter;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.io.IOException;
import java.util.Map;
import lombok.Builder;
import lombok.Getter;

@Entity
@Getter
//@Setter
public class Videos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "video_id")
    private Long id;
    private String url;
    private String titile;
    private String thumnail_url;
    private Long price;
    @Column(name = "video_rank")
    private Long rank;
    private String platform_name;
    private Long count;
    private String teck_stack;
    public Videos(){
    }
    @Builder
    public Videos(Long id,String url,String title,String thumnail_url,Long price,Long rank,String platform_name,Long count,String teck_stack){
        this.id = id;
        this.count = count;
        this.platform_name = platform_name;
        this.rank = rank;
        this.thumnail_url = thumnail_url;
        this.price = price;
        this.teck_stack = teck_stack;
        this.url = url;
        this.titile = title;


    }

}
