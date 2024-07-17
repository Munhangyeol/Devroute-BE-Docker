package com.teamdevroute.devroute.video.constans;

import org.springframework.beans.factory.annotation.Value;

public final class ApiConstans {
    @Value("${youtube.api.url.search}")
    public static String YOUTUBE_API_URL_SEARCH;
    @Value("${udemy.api.url.search}")
    public static String UDEMY_API_URL_SEARCH;
    public static String YOUTUBE_API_URL_FRONT_VIDEOID="https://www.youtube.com/watch?v=";
    public static String UDEMY_API_URL_FRONT_VIDEOID="https://www.udemy.com";

    public static String INFREAN_CRAWRLING_URL_SEARCH="https://www.inflearn.com/courses/it-programming/web-dev?skill=";
    public static String QUERY_FRONT_VALUE="&q=";
    public static String QUERY_FRONT_KEY = "&key=";
    public static String QUERY_MAX_RESULT = "&maxResults=10";
    public static String QUERY_UDEMY_SET_FEILD = "&fields[course]=title,url,price,image_125_H";

    private  ApiConstans(){

    }
}
