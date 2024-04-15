package capstone.replyRecoommend.gpt.converter;

import capstone.replyRecoommend.gpt.dto.GptResponse;
import capstone.replyRecoommend.gpt.dto.ReplyResponseDTO;

public class GptConverter {
    public static ReplyResponseDTO.PostReplyRecommend toPostReplyRecommend(GptResponse response){
        return ReplyResponseDTO.PostReplyRecommend.builder()
                .message(response.getChoices().get(response.getChoices().size()-1).getMessage().getContent())
                .build();
    }
}
