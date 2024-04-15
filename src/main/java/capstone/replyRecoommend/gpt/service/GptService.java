package capstone.replyRecoommend.gpt.service;

import capstone.replyRecoommend.gpt.dto.ReplyRequestDTO;
import capstone.replyRecoommend.gpt.dto.ReplyResponseDTO;

public interface GptService {
    ReplyResponseDTO.PostReplyRecommend postReplyRecommend(ReplyRequestDTO.PostReplyRecommend replyRecommend);
}
