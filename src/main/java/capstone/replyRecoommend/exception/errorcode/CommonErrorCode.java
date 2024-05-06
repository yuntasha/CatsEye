package capstone.replyRecoommend.exception.errorcode;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum CommonErrorCode implements ErrorCode{
    // 공용 처리
    INVALID_PARAMETER(HttpStatus.BAD_REQUEST, "C-400", "Invalid parameter included"),
    RESOURCE_NOT_FOUND(HttpStatus.NOT_FOUND, "C-404", "Resource not exists"),
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "C-500", "알수없는 에러 관리자에게 문의"),
//    ExpiredJwtException : JWT를 생성할 때 지정한 유효기간 초과할 때.
//    UnsupportedJwtException : 예상하는 형식과 일치하지 않는 특정 형식이나 구성의 JWT일 때
//    MalformedJwtException : JWT가 올바르게 구성되지 않았을 때
//    SignatureException :  JWT의 기존 서명을 확인하지 못했을 때
    // 인증 처리
    UNSUPPORTED_TOKEN(HttpStatus.UNAUTHORIZED, "JWT-401", "지원하지 않는 토큰"),
    EXPIRED_TOKEN(HttpStatus.UNAUTHORIZED, "JWT-401", "만료된 토큰"),
    ;

    private final HttpStatus httpStatus;
    private final String code;
    private final String message;
}
