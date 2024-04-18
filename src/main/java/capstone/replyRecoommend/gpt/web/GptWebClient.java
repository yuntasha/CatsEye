package capstone.replyRecoommend.gpt.web;

import capstone.replyRecoommend.gpt.web.dto.GptRequest;
import capstone.replyRecoommend.gpt.web.dto.GptResponse;
import capstone.replyRecoommend.gpt.dto.ReplyRequestDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

@Slf4j
public class GptWebClient {
    private WebClient webClient;
    private String recommendModel;

    public GptWebClient(String gptKey, String gptUrl, String recommendModel){
        this.webClient = WebClient.builder()
                .defaultHeaders(httpHeaders -> {
                    httpHeaders.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
                    httpHeaders.add(HttpHeaders.AUTHORIZATION, "Bearer " + gptKey);
                })
                .baseUrl(gptUrl)
                .build();
        this.recommendModel = recommendModel;
    }

    public GptResponse replyRecommend(ReplyRequestDTO.PostReplyRecommend postReplyRecommend){
        String message = makeMessage(postReplyRecommend);
        GptRequest request = new GptRequest(recommendModel, message, postReplyRecommend.getLength());
        GptResponse response = webClient.post()
                .bodyValue(request)
                .retrieve()
                .bodyToMono(GptResponse.class).block();
        return response;
    }


    private static String makeMessage(ReplyRequestDTO.PostReplyRecommend replyRecommend) {
        if (replyRecommend.getContent().isBlank()){
            log.info("exist content");
            return String.format("%s 상황인데, %s, 이렇게 답장하려고 하는데 답장에 대해서 조금 더 수정해줘. tone: %s, writing styLe: %s",
                    replyRecommend.getSituation(), replyRecommend.getContent(), replyRecommend.getTone(), replyRecommend.getWritingStyle());
        }
        else{
            log.info("not exist content");
            return String.format("%s 상황인데, 어떻게 답장을 할지 추천해줘. tone: %s, writing styLe: %s",
                    replyRecommend.getSituation(), replyRecommend.getTone(), replyRecommend.getWritingStyle());
        }
    }
}
