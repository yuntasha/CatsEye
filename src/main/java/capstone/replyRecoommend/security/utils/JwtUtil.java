package capstone.replyRecoommend.security.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;

public class JwtUtil {

    public static Long getId(String token, String secretKey){
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token)
                .getBody().get("userId", Long.class);
    }

    public static boolean isExpired(String token, String secretKey) throws Exception{
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token)
                .getBody().getExpiration().before(new Date());
    }

    public static String createJwt(Long userId, String secretKey, Long expiredMs){
        Claims claims = Jwts.claims();
        claims.put("userId", userId);

        return Jwts.builder()
                .setClaims(claims) // 유저네임이라는 클레임 공간에 들어가는 것
                .setIssuedAt(new Date(System.currentTimeMillis())) // 만든 시간
                .setExpiration(new Date(System.currentTimeMillis() + expiredMs)) // 만료 시간
                .signWith(SignatureAlgorithm.HS256, secretKey) // signature 제작
                .compact();
    }
}
