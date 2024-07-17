package com.teamdevroute.devroute.global.config;

import com.teamdevroute.devroute.crawling.InfreanVideoCrawling;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class AppConfig {

    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }
    @Bean
    public InfreanVideoCrawling infreanVideoCrawling(){
        return new InfreanVideoCrawling();
    }
}
