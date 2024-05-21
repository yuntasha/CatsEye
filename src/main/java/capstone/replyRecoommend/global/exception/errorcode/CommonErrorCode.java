package capstone.replyRecoommend.global.exception.errorcode;

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

    //user error (4001~
    USER_NOT_FOUND(HttpStatus.NOT_FOUND,"4001","해당 유저를 찾을 수 없습니다."),

    //pet error(4010~
    PET_NOT_FOUND(HttpStatus.NOT_FOUND,"4001","반려동물을 찾을 수 없습니다."),


    //chat error(4050~
    MAP_ALREADY(HttpStatus.CONFLICT,"4050","이미 해당 채팅이 시작됐습니다. 다시 시작해주세요."),
    MAP_NOT_FOUND(HttpStatus.NOT_FOUND,"4051","해당 채팅이 존재하지 않습니다."),

    //file error(4060~
    FILE_CHANGE_ERROR(HttpStatus.INTERNAL_SERVER_ERROR,"4060","파일 전환이 실패되었습니다."),


    ;

    private final HttpStatus httpStatus;
    private final String code;
    private final String message;
}
