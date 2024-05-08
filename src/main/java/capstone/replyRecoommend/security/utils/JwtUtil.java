package capstone.replyRecoommend.security.utils;

import capstone.replyRecoommend.exception.BusinessException;
import capstone.replyRecoommend.exception.errorcode.CommonErrorCode;
import capstone.replyRecoommend.security.JwtException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

import static capstone.replyRecoommend.security.JwtException.EXPIRED_TOKEN;
import static capstone.replyRecoommend.security.JwtException.UNSUPPORTED_TOKEN;

@Component
@RequiredArgsConstructor
public class JwtUtil {

    public static Long getId(String token, String secretKey){
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token)
                .getBody().get("userId", Long.class);
    }

    public static boolean isExpired(String token, String secretKey, HttpServletRequest request){
        try{
            return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token)
                    .getBody().getExpiration().before(new Date());
        } catch (ExpiredJwtException e) {
            request.setAttribute("exception", EXPIRED_TOKEN);
            return false;
        } catch (Exception e) {
            request.setAttribute("exception", UNSUPPORTED_TOKEN);
            return false;
        }
    }

    public static boolean isExpired(String token, String secretKey){
        try{
            return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token)
                    .getBody().getExpiration().before(new Date());
        } catch (ExpiredJwtException e) {
            throw new BusinessException(CommonErrorCode.EXPIRED_TOKEN);
        } catch (Exception e) {
            throw new BusinessException(CommonErrorCode.UNSUPPORTED_TOKEN);
        }
    }
}
