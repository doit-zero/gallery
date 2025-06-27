package kr.co.wikibook.gallery.common.util;



import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.StringUtils;

import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class TokenUtils {
    private static final Key signKey;


    static {
        String secretKey = "gallery_2025_2024_2023_sjanskehwoalTsms!!rhdnqek#$%";
        byte[] secretKeyBytes = secretKey.getBytes(StandardCharsets.UTF_8);
        signKey = new SecretKeySpec(secretKeyBytes, SignatureAlgorithm.HS256.getJcaName());
    }

    // 토큰 발급
    public static String generate(String subject,String name,Object value,int expMinutes){
        // 만료 시간 설정
        Date expTime = new Date();

        // 분(minute)을 밀리초(millisecond)로 변환해 입력
        expTime.setTime(expTime.getTime() + 1000L * 60 * expMinutes);

        // 기본 정보 입력
        HashMap<String,Object> headerMap = new HashMap<>();
        headerMap.put("typ","JWT");
        headerMap.put("alg","HS256");

        // 클레임 입력
        HashMap<String, Object> claims = new HashMap<>();
        claims.put(name,value);

        // 토큰 발급
        JwtBuilder builder = Jwts.builder()
                .setHeader(headerMap)
                .setSubject(subject)
                .setExpiration(expTime)
                .addClaims(claims)
                .signWith(signKey, SignatureAlgorithm.HS256);

        return builder.compact();
    }

    public static boolean isValid(String token){
        // 토큰 값이 있다면
        if(StringUtils.hasLength(token)){
            try {
                // JWT 토큰을 검증 parseClaimsJws 전달된 token을 파싱하고 서명을 검증하고 유효하지 않으면 예외를 발생 시킴
                Jwts.parserBuilder().setSigningKey(signKey).build().parseClaimsJws(token);
                return true;
            } catch (ExpiredJwtException e) { // 만료됨
                e.printStackTrace();
            } catch (JwtException e){ // 유효하지 않음
                e.printStackTrace();
            }
        }
        return false;
    }

    /**
     * 목적 : JWT토큰을 파싱해서 서명을 검증하고 claims을 Map형태로 반환
     * PayLoad 부분에 Json 형태로 들어간 claims를 파싱하는 것임
     */
    public static Map<String,Object> getBody(String token){
        return Jwts.parserBuilder().setSigningKey(signKey).build().parseClaimsJws(token).getBody();
    }
}
