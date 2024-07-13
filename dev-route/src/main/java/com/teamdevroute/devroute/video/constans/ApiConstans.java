package com.teamdevroute.devroute.video.constans;

import org.springframework.beans.factory.annotation.Value;

public final class ApiConstans {

    @Value("${youtube.api.url.search}")
    public static String YOUTUBE_API_URL_SEARCH;
    public static String YOUTUBE_API_URL_FRONT_VIDEOID="https://www.youtube.com/watch?v=";
    public static String QUERY_FRONT_VALUE="&q=";
    public static String QUERY_FRONT_KEY = "&key=";
    public static String QUERY_MAX_RESULT = "&maxResults=10";


    private  ApiConstans(){

    }
}
