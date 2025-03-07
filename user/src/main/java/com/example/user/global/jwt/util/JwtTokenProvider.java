package com.example.user.global.jwt.util;

import com.example.user.domain.auth.domain.Refresh;
import com.example.user.domain.auth.domain.repository.RefreshRepository;
import com.example.user.domain.auth.service.CustomUserDetailsService;
import com.example.user.global.config.properties.JwtProperties;
import com.example.user.global.jwt.exception.ExpiredTokenException;
import com.example.user.global.jwt.exception.InvalidTokenException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Component
@RequiredArgsConstructor
public class JwtTokenProvider {

    private final JwtProperties jwtProperties;
    private final CustomUserDetailsService customUserDetailsService;
    private final RefreshRepository refreshRepository;

    @Value("${spring.jwt.access.expiration}")
    private long accessTokenExpiration;
    @Value("${spring.jwt.refresh.expiration}")
    private long refreshTokenExpiration;

    private static final String ACCESS_KEY = "access_token";
    private static final String REFRESH_KEY = "refresh_token";

    public String createAccessToken(String email) {
        return createToken(email, ACCESS_KEY, jwtProperties.getAccessTime());
    }

    @Transactional
    public String createRefreshToken(Long userId, String email) {

        String token = createToken(email, REFRESH_KEY, jwtProperties.getRefreshTime());
        Date date = new Date(System.currentTimeMillis() + refreshTokenExpiration);

        refreshRepository.save(
                Refresh.builder()
                        .userId(userId)
                        .refreshToken(token)
                        .expiration(date.toString())
                        .build()
        );
        return token;
    }

    private String createToken(String email, String type, Long time) {
        Date now = new Date();
        return Jwts.builder().signWith(SignatureAlgorithm.HS256, jwtProperties.getSecretKey())
                .setSubject(email)
                .setHeaderParam("typ", type)
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime() + time))
                .compact();
    }

    public String resolveToken(HttpServletRequest request) {
        String bearer = request.getHeader("Authorization");
        return parseToken(bearer);
    }

    public String parseToken(String bearerToken) {
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            return bearerToken.replace("Bearer ", "");
        }
        return null;
    }

    public UsernamePasswordAuthenticationToken authorization(String token) {
        UserDetails userDetails = customUserDetailsService.loadUserByUsername(getTokenSubject(token));
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

    private String getTokenSubject(String subject) {
        return getTokenBody(subject).getSubject();
    }

    private Claims getTokenBody(String token) {
        try {
            return Jwts.parser()
                    .setSigningKey(jwtProperties.getSecretKey())
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
        } catch(ExpiredTokenException e) {
            throw new ExpiredTokenException();
        } catch (Exception e) {
            throw new InvalidTokenException();
        }
    }
}
