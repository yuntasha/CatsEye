package capstone.replyRecoommend.gpt.controller;

import capstone.replyRecoommend.global.response.SuccessResponse;
import capstone.replyRecoommend.gpt.dto.ChatDtoReq;
import capstone.replyRecoommend.gpt.dto.ChatDtoRes;
import capstone.replyRecoommend.gpt.service.GptService;
import capstone.replyRecoommend.security.utils.JwtUtil;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/chat")
public class GptController {

    private final GptService gptService;
    /**
     * 작성자 : 정주현
     * 초기 대화 시작
     */
    @PostMapping("/init")
    public SuccessResponse<ChatDtoRes.ChatAnwDtoRes> initChat(@RequestBody @Valid  ChatDtoReq.initChatReq reuqest,
                                                              @AuthenticationPrincipal Long userId) {
        return SuccessResponse.success(gptService.initChat(userId,reuqest));
    }
    /**
     * 작성자 : 정주현
     * 이후 대화 진행
     */
    @PostMapping("")
    public SuccessResponse<ChatDtoRes.ChatAnwDtoRes> chat(@RequestBody @Valid ChatDtoReq.chatReq request,
                                                          @AuthenticationPrincipal Long userId){
        return SuccessResponse.success(gptService.chat(userId,request));
    }
    /**
     * 작성자 : 정주현
     * 채팅방 삭제
     */
    @PatchMapping("/remove")
    public SuccessResponse<String> removeChat(@AuthenticationPrincipal Long userId){
        gptService.removeChat(userId);
        return SuccessResponse.successWithoutResult("성공");
    }

}