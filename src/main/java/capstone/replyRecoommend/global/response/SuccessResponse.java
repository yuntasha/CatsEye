package capstone.replyRecoommend.global.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;


@Getter
public class SuccessResponse<T> extends ApiResponse{

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private final T data;

    private SuccessResponse(T data){
        super(true, "200", "OK");
        this.data = data;
    }

    public static <T> SuccessResponse<T> success(T data){
        return new SuccessResponse<>(data);
    }
}
