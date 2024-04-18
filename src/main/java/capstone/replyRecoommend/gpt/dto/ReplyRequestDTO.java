package capstone.replyRecoommend.gpt.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

public class ReplyRequestDTO {
    @Getter
    public static class PostReplyRecommend{
        @NotNull
        @NotBlank
        String situation;
        String content;
        @NotNull
        @NotBlank
        String tone;
        @NotNull
        @NotBlank
        String writingStyle;
        @NotNull
        @Min(50)
        @Max(200)
        Integer length;
    }
}
