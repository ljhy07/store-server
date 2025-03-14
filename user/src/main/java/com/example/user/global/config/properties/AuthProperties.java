package com.example.user.global.config.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Setter
@Getter
@Configuration
@ConfigurationProperties("auth")
public class AuthProperties {

    private Google google;
    private Kakao kakao;
    private Naver naver;

    @Getter
    @Setter
    public static class Google {
        private String baseUrl;
        private String clientId;
        private String secretId;
        private String redirectUrl;
    }

    @Getter
    @Setter
    public static class Kakao {
        private String baseUrl;
        private String clientId;
        private String secretId;
        private String redirectUrl;
    }

    @Getter
    @Setter
    public static class Naver {
        private String baseUrl;
        private String clientId;
        private String secretId;
        private String redirectUrl;
    }

    public String getGoogleBaseUrl() {
        return google.getBaseUrl();
    }

    public String getGoogleClientId() {
        return google.getClientId();
    }

    public String getGoogleSecretId() {
        return google.getSecretId();
    }

    public String getGoogleRedirectUrl() {
        return google.getRedirectUrl();
    }

    public String getKakaoBaseUrl() {
        return kakao.getBaseUrl();
    }

    public String getKakaoClientId() {
        return kakao.getClientId();
    }

    public String getKakaoSecretId() {
        return kakao.getSecretId();
    }

    public String getKakaoRedirectUrl() {
        return kakao.getRedirectUrl();
    }

    public String getNaverBaseUrl() {
        return naver.getBaseUrl();
    }

    public String getNaverClientId() {
        return naver.getClientId();
    }

    public String getNaverSecretId() {
        return naver.getSecretId();
    }

    public String getNaverRedirectUrl() {
        return naver.getRedirectUrl();
    }
}
