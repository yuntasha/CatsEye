package capstone.replyRecoommend.security;

import capstone.replyRecoommend.global.response.ErrorResponse;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import java.io.IOException;

import static org.springframework.http.HttpStatus.*;


@Getter
@AllArgsConstructor
public enum JwtException {
    UNSUPPORTED_TOKEN(UNAUTHORIZED, "AUTH-401", "잘못된 JWT토큰입니다."),
    EXPIRED_TOKEN(UNAUTHORIZED, "AUTH-401", "만료된 JWT토큰입니다."),
    NOTFOUND_TOKEN(UNAUTHORIZED, "AUTH-401", "토큰을 찾을 수 없습니다."),
    ACCESS_DENIED(FORBIDDEN, "AUTH-402", "권한이 없습니다"),
    ;

    private final HttpStatus httpStatus;
    private final String code;
    private final String message;

    public void setResponse(HttpServletResponse response) throws IOException {
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(String.valueOf(createJwtExceptionBody(this)));
    }

    private static ErrorResponse createJwtExceptionBody(JwtException exception) {
        return ErrorResponse.of(exception);
    }
}
