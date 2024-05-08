package capstone.replyRecoommend.gpt.dto;


import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class ChatDtoReq {

    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class initChatReq{
        @NotBlank(message = "대상을 입력해주세요.")
        private String name;
        @NotBlank(message = "상황을 입력해주세요.")
        private String situation;
        private String message;
        @NotBlank(message = "말투을 선택해주세요.")
        private String tone;
        @NotBlank(message = "문체를 선택해주세요.")
        private String writingStyle;
        @Min(50)
        @Max(200)
        private Integer length;
    }
    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class chatReq{
        @NotBlank(message = "메세지를 입력해주세요.")
        private String message;
    }


}
