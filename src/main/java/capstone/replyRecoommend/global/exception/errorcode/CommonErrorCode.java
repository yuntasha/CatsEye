package capstone.replyRecoommend.global.exception.errorcode;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum CommonErrorCode implements ErrorCode{
    // 공용 처리
    INVALID_PARAMETER(HttpStatus.BAD_REQUEST, "4000", "Invalid parameter included"),
    RESOURCE_NOT_FOUND(HttpStatus.NOT_FOUND, "4040", "Resource not exists"),
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "5000", "알수없는 에러 관리자에게 문의"),

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

    //hospital (4070~
    REGION_NOT_FOUND(HttpStatus.NOT_FOUND, "4070", "해당 지역의 파일이 없습니다."),
    DATA_FILE_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "4071", "병원 파일 파싱중 에러, 서버관리자에게 문의하세요"),

    ;

    private final HttpStatus httpStatus;
    private final String code;
    private final String message;
}
