package capstone.replyRecoommend.global.security;

import capstone.replyRecoommend.global.auth.dto.TokenMapper;
import capstone.replyRecoommend.global.exception.BusinessException;
import capstone.replyRecoommend.global.exception.errorcode.CommonErrorCode;
import capstone.replyRecoommend.global.security.utils.JwtUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Base64;
import java.util.Date;

@Component
public class JwtFactory {

    private final String accessKey;
    private final String refreshKey;

    private final Long accessExpiredMs; // 5분
    private final Long refreshExpiredMs; // 10분

    public JwtFactory(@Value("${jwt.access}") String accessKey, @Value("${jwt.access}") String refreshKey) {
        this.accessKey = Base64.getEncoder().encodeToString(accessKey.getBytes());
        this.refreshKey = Base64.getEncoder().encodeToString(refreshKey.getBytes());
        this.accessExpiredMs = 5 * 60 * 1000L;
        this.refreshExpiredMs = 10 * 60 * 1000L;
    }

    public TokenMapper generateBothToken(Long userId){
        return new TokenMapper(
                generateToken(userId, accessKey, accessExpiredMs),
                generateToken(userId, refreshKey, refreshExpiredMs)
        );
    }

    private String generateToken(Long userId, String secretKey, Long expiredMs){
        Claims claims = Jwts.claims();
        claims.put("userId", userId);

        return Jwts.builder()
                .setClaims(claims) // 유저네임이라는 클레임 공간에 들어가는 것
                .setIssuedAt(new Date(System.currentTimeMillis())) // 만든 시간
                .setExpiration(new Date(System.currentTimeMillis() + expiredMs)) // 만료 시간
                .signWith(SignatureAlgorithm.HS256, secretKey) // signature 제작
                .compact();
    }

    public TokenMapper reissueToken(String refreshToken){
        if (!JwtUtil.isExpired(refreshToken, refreshKey)){
            Long userId = JwtUtil.getId(refreshToken, refreshKey);
            return new TokenMapper(
                    generateToken(userId, accessKey, accessExpiredMs),
                    generateToken(userId, refreshKey, refreshExpiredMs)
            );
        }
        else throw new BusinessException(CommonErrorCode.EXPIRED_TOKEN);
    }
}
