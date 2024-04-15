package capstone.replyRecoommend.gpt.service;

import capstone.replyRecoommend.gpt.config.GptConfig;
import capstone.replyRecoommend.gpt.config.GptWebClient;
import capstone.replyRecoommend.gpt.converter.GptConverter;
import capstone.replyRecoommend.gpt.dto.GptResponse;
import capstone.replyRecoommend.gpt.dto.ReplyRequestDTO;
import capstone.replyRecoommend.gpt.dto.ReplyResponseDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
@Slf4j
@RequiredArgsConstructor
public class GptServiceImpl implements GptService{

    private final GptWebClient gptWebClient;

    public ReplyResponseDTO.PostReplyRecommend postReplyRecommend(ReplyRequestDTO.PostReplyRecommend replyRecommend){
        GptResponse gptResponse = gptWebClient.replyRecommend(replyRecommend);
        return GptConverter.toPostReplyRecommend(gptResponse);
    }


}
