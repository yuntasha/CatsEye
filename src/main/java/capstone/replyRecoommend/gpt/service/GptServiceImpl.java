package capstone.replyRecoommend.gpt.service;


import capstone.replyRecoommend.global.exception.BusinessException;
import capstone.replyRecoommend.global.exception.errorcode.CommonErrorCode;
import capstone.replyRecoommend.gpt.converter.GptConverter;
import capstone.replyRecoommend.gpt.dto.ChatDtoReq;
import capstone.replyRecoommend.gpt.dto.ChatDtoRes;
import capstone.replyRecoommend.gpt.web.GptWebClient;
import capstone.replyRecoommend.gpt.web.dto.GptRequest;
import capstone.replyRecoommend.gpt.web.dto.GptResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@Slf4j
@RequiredArgsConstructor
public class GptServiceImpl implements GptService{

    private static final String USER = "user";
    private static final String ASSISTANT = "assistant";
    private static final String SYSTEM = "system";


    private final GptWebClient gptWebClient;
    private final Map<Long, GptRequest> memberChatMap = new HashMap<>();

/*
    //초기 답변 추천
    @Override
    public ChatDtoRes.ChatAnwDtoRes initChat(Long userId, ChatDtoReq.initChatReq chatDtoReq){
        if(memberChatMap.containsKey(userId)){
            throw new BusinessException(CommonErrorCode.MAP_ALREADY);
        }
        GptRequest gptRequest = gptWebClient.of(500);
        addChatMessage(gptRequest,SYSTEM,"너는 고양이 눈과 관련된 질병을 알려주는 ai야.");

        String userMessage = makeMessage(chatDtoReq);

        addChatMessage(gptRequest,USER,userMessage);

        GptResponse gptResponse = gptWebClient.replyRecommend(gptRequest);

        String content = gptResponse.getChoices().get(0).getMessage().getContent();

        addChatMessage(gptRequest,ASSISTANT,content);
        memberChatMap.put(userId,gptRequest);

        System.out.println(memberChatMap);
        return GptConverter.answer(content);

    }

 */

    //이어서 말하기
    @Override
    public ChatDtoRes.ChatAnwDtoRes chat(Long userId, ChatDtoReq.chatReq request){
        GptRequest gptRequest;
        if(memberChatMap.get(userId) == null){
            gptRequest = gptWebClient.of(500);
            addChatMessage(gptRequest,SYSTEM,"너는 고양이 눈과 관련된 질병을 알려주는 ai야.");

        }else{
            gptRequest = memberChatMap.get(userId);
        }

        addChatMessage(gptRequest,USER,request.getMessage());

        GptResponse gptResponse = gptWebClient.assistantRes(gptRequest);

        String content = gptResponse.getChoices().get(0).getMessage().getContent();

        addChatMessage(gptRequest,ASSISTANT,content);
        memberChatMap.put(userId,gptRequest);

        return GptConverter.answer(content);

    }

    //해당 대화내용 삭제
    public void removeChat(Long userId){
        if(!memberChatMap.containsKey(userId)){
            throw new BusinessException(CommonErrorCode.MAP_NOT_FOUND);
        }
        memberChatMap.remove(userId);
    }

    //메세지 추가
    private void addChatMessage(GptRequest gptRequest,String role,String message){
        gptRequest.addMessage(role,message);
    }

/*
    private String makeMessage(ChatDtoReq.initChatReq chatDtoReq) {
        if (!chatDtoReq.getMessage().isBlank()){
            log.info("exist content");
            return String.format("%s 상황인데, %s, 이렇게 답장하려고 하는데 답장에 대해서 조금 더 수정해줘. tone: %s, writing styLe: %s",
                    chatDtoReq.getSituation(), chatDtoReq.getMessage(), chatDtoReq.getTone(), chatDtoReq.getWritingStyle());
        }
        else{
            log.info("not exist content");
            return String.format("%s 상황인데, 어떻게 답장을 할지 추천해줘. tone: %s, writing styLe: %s",
                    chatDtoReq.getSituation(), chatDtoReq.getTone(), chatDtoReq.getWritingStyle());
        }
    }


 */

}
