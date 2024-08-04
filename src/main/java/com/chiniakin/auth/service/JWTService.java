package com.chiniakin.auth.service;

import com.chiniakin.auth.entity.User;
import com.chiniakin.auth.repository.UserRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Сервис для работы с JWT токенами.
 *
 * @author ChiniakinD
 */
@Service
@RequiredArgsConstructor
public class JWTService {

    @Value("${token.signing.key}")
    private String jwtSigningKey;

    private final UserRepository userRepository;

    /**
     * Получает имя пользоватедля из токена.
     *
     * @param token JWT токен.
     * @return имя пользователя.
     */
    public String extractUserName(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    /**
     * Создает токен для пользователя.
     *
     * @param user пользователь.
     * @return токен.
     */
    public String generateToken(User user) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("id", user.getId());
        claims.put("email", user.getEmail());
        claims.put("role", userRepository.findRolesByLogin(user.getLogin()).stream()
                .map(role -> role.getRole().name())
                .collect(Collectors.toList()));
        user.setRoles(userRepository.findRolesByLogin(user.getLogin()));
        return generateToken(claims, user);
    }

    /**
     * Получает данный параметр из токена
     *
     * @param token           JWT токен
     * @param claimsResolvers функция для получения.
     * @return требование из токена.
     */
    private <T> T extractClaim(String token, Function<Claims, T> claimsResolvers) {
        final Claims claims = extractAllClaims(token);
        return claimsResolvers.apply(claims);
    }

    /**
     * Создает токен в дополнительными данными.
     *
     * @param extraClaims дополнительные параметры ддля токена
     * @param user        пользователь.
     * @return сгенерированный токен
     */
    private String generateToken(Map<String, Object> extraClaims, User user) {
        return Jwts.builder()
                .setClaims(extraClaims)
                .setSubject(user.getLogin())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 100000 * 60 * 24))
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    /**
     * Получает все параметры из токена.
     *
     * @param token JWT токен.
     * @return все параметры из токена.
     */
    private Claims extractAllClaims(String token) {
        return Jwts.parser().setSigningKey(getSigningKey()).build().parseClaimsJws(token)
                .getBody();
    }

    /**
     * Получет ключ для подписания JWT токена.
     *
     * @return ключ.
     */
    private Key getSigningKey() {
        byte[] keyBytes = Decoders.BASE64.decode(jwtSigningKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }

}
