package capstone.replyRecoommend.gpt.controller;

import capstone.replyRecoommend.exception.BusinessException;
import capstone.replyRecoommend.exception.errorcode.CommonErrorCode;
import capstone.replyRecoommend.gpt.dto.ReplyRequestDTO;
import capstone.replyRecoommend.gpt.dto.ReplyResponseDTO;
import capstone.replyRecoommend.gpt.service.GptService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class GptController {

    private final GptService gptService;


    @PostMapping("/chat")
    public ReplyResponseDTO.PostReplyRecommend chat(@RequestBody @Valid ReplyRequestDTO.PostReplyRecommend replyRecommend){
        return gptService.postReplyRecommend(replyRecommend);
    }

    @GetMapping("/test")
    public void test(){
        throw new BusinessException(CommonErrorCode.RESOURCE_NOT_FOUND);
    }
}