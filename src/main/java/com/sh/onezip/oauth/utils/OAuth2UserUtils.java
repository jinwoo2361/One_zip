package com.sh.onezip.oauth.utils;

import com.sh.onezip.member.entity.Member;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.Map;

public class OAuth2UserUtils {
    public static Member of(OAuth2User oAuth2User, String provider) {
        return switch(provider) {
            case "google" -> google(oAuth2User);
            case "kakao" -> kakao(oAuth2User);
            default -> throw new AssertionError(provider);
        };
    }

    private static Member google(OAuth2User oAuth2User) {
        String memberId = oAuth2User.getAttribute("sub") + "@google";
        String name = oAuth2User.getAttribute("name");
//        String email = oAuth2User.getAttribute("email");
        return Member.builder()
                .memberId(memberId) // .username(username) 대신 .memberId(memberId) 사용
                .password("1234") // 실제 애플리케이션에서는 암호화된 비밀번호를 사용하세요
                .name(name)
//                .email(email)
                .build();
    }


    private static Member kakao(OAuth2User oAuth2User) {
        String memberId = oAuth2User.getAttribute("id") + "@kakao";
        Map<String, Object> properties = oAuth2User.getAttribute("properties");
        String name = (String) properties.get("nickname");
        return Member.builder()
                .memberId(memberId)
                .password("1234")
                .name(name)
                .build();
    }

    public static String getUsername(OAuth2User oAuth2User, String provider) {
        return switch(provider) {
            case "google" -> oAuth2User.getAttribute("sub") + "@google";
            case "kakao" -> oAuth2User.getAttribute("id") + "@kakao";
            default -> throw new AssertionError(provider);
        };
    }
}
