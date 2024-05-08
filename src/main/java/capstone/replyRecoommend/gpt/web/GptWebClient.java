package capstone.replyRecoommend.gpt.web;

import capstone.replyRecoommend.gpt.web.dto.GptRequest;
import capstone.replyRecoommend.gpt.web.dto.GptResponse;
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

    public GptRequest of(int length){
       return new GptRequest(recommendModel,length);
    }

    public GptResponse replyRecommend(GptRequest gptRequest){
        return webClient.post()
                .bodyValue(gptRequest)
                .retrieve()
                .bodyToMono(GptResponse.class).block();
    }




}
