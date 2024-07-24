package com.teamdevroute.devroute.global.auth;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.teamdevroute.devroute.user.dto.UserAuthResponse;
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

import java.math.BigInteger;
import java.security.SecureRandom;

@Component
@RequiredArgsConstructor
public class Oauth2Util {

    // kakao
    @Value("${kakao.api_key}")
    private String KAKAO_API_KEY;

    @Value("${kakao.redirect_uri}")
    private String KAKAO_REDIRECT_URI;

    @Value("${kakao.token_url}")
    private String KAKAO_TOKEN_URL;

    @Value("${kakao.userinfo_url}")
    private String KAKAO_USERINFO_URL;

    @Value("${kakao.authorization_url}")
    private String KAKAO_AUTHORIZATION_URL;

    // google
    @Value("${google.client.id}")
    private String GOOGLE_CLIENT_ID;

    @Value("${google.redirect.uri}")
    private String GOOGLE_REDIRECT_URL;

    @Value("${google.client.secret}")
    private String GOOGLE_CLIENT_SECRET;

    @Value("${google.token_url}")
    private String GOOGLE_TOKEN_URL;

    @Value("${google.userinfo_url}")
    private String GOOGLE_USERINFO_URL;

    @Value("${google.authorization_url}")
    private String GOOGLE_AUTHORIZATION_URL;

    // naver
    @Value("${naver.client.id}")
    private String NAVER_CLIENT_ID;

    @Value("${naver.redirect.uri}")
    private String NAVER_REDIRECT_URL;

    @Value("${naver.client.secret}")
    private String NAVER_CLIENT_SECRET;

    @Value("${naver.token_url}")
    private String NAVER_TOKEN_URL;

    @Value("${naver.userinfo_url}")
    private String NAVER_USERINFO_URL;

    @Value("${naver.authorization_url}")
    private String NAVER_AUTHORIZATION_URL;


    private static final RestTemplate restTemplate = new RestTemplate();

    public String getKakaoRedirectUrl() {
        return KAKAO_AUTHORIZATION_URL
                + "?client_id=" + KAKAO_API_KEY
                + "&redirect_uri=" + KAKAO_REDIRECT_URI
                + "&response_type=code";
    }

    public String getGoogleRedirectUrl() {
        return GOOGLE_AUTHORIZATION_URL
                + "?client_id=" + GOOGLE_CLIENT_ID
                + "&redirect_uri=" + GOOGLE_REDIRECT_URL
                + "&response_type=code&scope=email%20profile%20openid"
                + "&access_type=offline";
    }

    public String getNaverRedirectUrl() {
        return NAVER_AUTHORIZATION_URL
                + "?client_id=" + NAVER_CLIENT_ID
                + "&redirect_uri=" + NAVER_REDIRECT_URL
                + "&response_type=code"
                + "&state" + generateState();
    }

    public String getKakaoAccessToken(String authorizationCode) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("grant_type", "authorization_code");
        params.add("client_id", KAKAO_API_KEY);
        params.add("redirect_uri", KAKAO_REDIRECT_URI);
        params.add("code", authorizationCode);

        HttpEntity<MultiValueMap<String, String>> kakaoTokenRequest = new HttpEntity<>(params, httpHeaders);

        ResponseEntity<String> response = restTemplate.exchange(
                KAKAO_TOKEN_URL,
                HttpMethod.POST,
                kakaoTokenRequest,
                String.class
        );

        return JsonParser.parseString(response.getBody()).getAsJsonObject().get("access_token").getAsString();
    }

    public String getGoogleAccessToken(String authorizationCode) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("grant_type", "authorization_code");
        params.add("client_id", GOOGLE_CLIENT_ID);
        params.add("client_secret", GOOGLE_CLIENT_SECRET);
        params.add("redirect_uri", GOOGLE_REDIRECT_URL);
        params.add("code", authorizationCode);

        HttpEntity<MultiValueMap<String, String>> googleTokenRequest = new HttpEntity<>(params, httpHeaders);

        ResponseEntity<String> response = restTemplate.exchange(
                GOOGLE_TOKEN_URL,
                HttpMethod.POST,
                googleTokenRequest,
                String.class
        );

        return JsonParser.parseString(response.getBody()).getAsJsonObject().get("access_token").getAsString();
    }

    public String getNaverAccessToken(String authorizationCode, String state) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("grant_type", "authorization_code");
        params.add("client_id", NAVER_CLIENT_ID);
        params.add("client_secret", NAVER_CLIENT_SECRET);
        params.add("code", authorizationCode);
        params.add("state", state);

        HttpEntity<MultiValueMap<String, String>> naverTokenRequest = new HttpEntity<>(params, httpHeaders);

        ResponseEntity<String> response = restTemplate.exchange(
                NAVER_TOKEN_URL,
                HttpMethod.POST,
                naverTokenRequest,
                String.class
        );

        return JsonParser.parseString(response.getBody()).getAsJsonObject().get("access_token").getAsString();
    }

    public UserAuthResponse getKakaoUserInformation(String accessToken) {
        HttpHeaders httpHeaders = new HttpHeaders();

        httpHeaders.add("Authorization", "Bearer " + accessToken);
        httpHeaders.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

        HttpEntity<MultiValueMap<String, String>> kakaoProfileRequest = new HttpEntity<>(httpHeaders);

        ResponseEntity<String> response = restTemplate.exchange(
                KAKAO_USERINFO_URL,
                HttpMethod.POST,
                kakaoProfileRequest,
                String.class
        );
        Gson gson = new Gson();
        JsonObject responseBody = gson.fromJson(response.getBody(), JsonObject.class);
        JsonObject properties = responseBody.getAsJsonObject("properties");
        JsonObject kakaoAccount = responseBody.getAsJsonObject("kakao_account");

        return UserAuthResponse.builder()
                .name(properties.get("nickname").getAsString())
                .email(kakaoAccount.get("email").getAsString())
                .build();
    }

    public UserAuthResponse getGoogleUserInformation(String accessToken) {
        HttpHeaders httpHeaders = new HttpHeaders();

        httpHeaders.add("Authorization", "Bearer " + accessToken);
        httpHeaders.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

        HttpEntity<MultiValueMap<String, String>> googleProfileRequest = new HttpEntity<>(httpHeaders);

        ResponseEntity<String> response = restTemplate.exchange(
                GOOGLE_USERINFO_URL,
                HttpMethod.GET,
                googleProfileRequest,
                String.class
        );

        Gson gson = new Gson();
        JsonObject responseBody = gson.fromJson(response.getBody(), JsonObject.class);

        return UserAuthResponse.builder()
                .name(responseBody.get("name").getAsString())
                .email(responseBody.get("email").getAsString())
                .build();
    }

    public UserAuthResponse getNaverUserInformation(String accessToken) {
        HttpHeaders httpHeaders = new HttpHeaders();

        httpHeaders.add("Authorization", "Bearer " + accessToken);
        httpHeaders.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

        HttpEntity<MultiValueMap<String, String>> naverProfileRequest = new HttpEntity<>(httpHeaders);

        ResponseEntity<String> response = restTemplate.exchange(
                NAVER_USERINFO_URL,
                HttpMethod.GET,
                naverProfileRequest,
                String.class
        );

        Gson gson = new Gson();
        JsonObject responseBody = gson.fromJson(response.getBody(), JsonObject.class);
        JsonObject responseDetails = responseBody.getAsJsonObject("response");

        return UserAuthResponse.builder()
                .name(responseDetails.get("name").getAsString())
                .email(responseDetails.get("email").getAsString())
                .build();
    }

    private String generateState() {
        SecureRandom random = new SecureRandom();
        return new BigInteger(130, random).toString(32);
    }
}
