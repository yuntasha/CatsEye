package capstone.replyRecoommend.security.utils;

import capstone.replyRecoommend.global.exception.BusinessException;
import capstone.replyRecoommend.global.exception.errorcode.CommonErrorCode;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

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
