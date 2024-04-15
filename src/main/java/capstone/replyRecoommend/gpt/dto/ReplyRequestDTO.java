package capstone.replyRecoommend.gpt.dto;

import lombok.Getter;

public class ReplyRequestDTO {
    @Getter
    public static class PostReplyRecommend{
        String situation;
        String content;
        String tone;
        String writingStyle;
        Integer length;
    }
}
