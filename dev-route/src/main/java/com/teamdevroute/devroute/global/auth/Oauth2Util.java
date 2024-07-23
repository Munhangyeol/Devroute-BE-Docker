package com.teamdevroute.devroute.global.auth;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.teamdevroute.devroute.user.dto.UserAuthResponse;
import com.teamdevroute.devroute.user.dto.UserCreateResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

@Component
@RequiredArgsConstructor
public class Oauth2Util {

    @Value("${kakao.api_key}")
    private String kakaoApiKey;

    @Value("${kakao.redirect_uri}")
    private String kakaoRedirectUri;

    private String KAKAO_TOKEN_URL = "https://kauth.kakao.com/oauth/token";
    private String KAKAO_USERINFO_URL = "https://kapi.kakao.com/v2/user/me";
    private String KAKAO_AUTHORIZATION_URL = "https://kauth.kakao.com/oauth/authorize";

    private static final RestTemplate restTemplate = new RestTemplate();

    public String getKakaoRedirectUrl() {
        String KAKAO_AUTHORIZATION_URL = "https://kauth.kakao.com/oauth/authorize";

        String url = KAKAO_AUTHORIZATION_URL
                + "?client_id=" + kakaoApiKey
                + "&redirect_uri=" + kakaoRedirectUri
                + "&response_type=code";
        return url;
    }

    public String getKakaoAccessToken(String authorizationCode) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("grant_type", "authorization_code");
        params.add("client_id", kakaoApiKey);
        params.add("redirect_uri", kakaoRedirectUri);
        params.add("code", authorizationCode);

        HttpEntity<MultiValueMap<String,String>> kakaoTokenRequest = new HttpEntity<>(params,httpHeaders);

        ResponseEntity<String> response = restTemplate.exchange(
                KAKAO_TOKEN_URL,
                HttpMethod.POST,
                kakaoTokenRequest,
                String.class
        );

        return JsonParser.parseString(response.getBody()).getAsJsonObject().get("access_token").getAsString();
    }

    public UserAuthResponse getKakaoUserInformation(String accessToken) {
        HttpHeaders httpHeaders = new HttpHeaders();

        httpHeaders.add("Authorization", "Bearer "+ accessToken);
        httpHeaders.add("Content-type","application/x-www-form-urlencoded;charset=utf-8");

        HttpEntity<MultiValueMap<String,String >> kakaoProfileRequest= new HttpEntity<>(httpHeaders);

        ResponseEntity<String> response = restTemplate.exchange(
                KAKAO_USERINFO_URL,
                HttpMethod.POST,
                kakaoProfileRequest,
                String.class
        );

        // 토큰을 사용하여 사용자 정보 추출
        JsonObject responseBody = JsonParser.parseString(response.getBody()).getAsJsonObject();
        JsonObject properties = responseBody.getAsJsonObject("properties");
        JsonObject kakaoAccount = responseBody.getAsJsonObject("kakao_account");

        String nickname = properties.getAsJsonObject().get("nickname").getAsString();
        String email = kakaoAccount.getAsJsonObject().get("email").getAsString();

        return UserAuthResponse.builder()
                .name(nickname)
                .email(email)
                .build();
    }


}
