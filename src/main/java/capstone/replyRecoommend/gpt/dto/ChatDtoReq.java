package capstone.replyRecoommend.gpt.dto;


import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class ChatDtoReq {


    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class chatReq{
        @NotBlank(message = "메세지를 입력해주세요.")
        private String message;
    }


}
