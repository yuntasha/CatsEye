package capstone.replyRecoommend.global.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;


@Getter
public class SuccessResponse<T> extends ApiResponse{

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private final T result;

    private SuccessResponse(T data){
        super(true, "200", "OK");
        this.result = data;
    }

    public static <T> SuccessResponse<T> success(T data){
        return new SuccessResponse<>(data);
    }
    public static <T> SuccessResponse<T> successWithoutResult(T data){
        return new SuccessResponse<>(null);
    }
}
