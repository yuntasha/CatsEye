package capstone.replyRecoommend.gpt.config;

import capstone.replyRecoommend.gpt.dto.GptRequest;
import capstone.replyRecoommend.gpt.dto.GptResponse;
import capstone.replyRecoommend.gpt.dto.ReplyRequestDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

@Slf4j
@Configuration
public class GptConfig {
    @Value("${gpt.api.key}")
    private String gptKey;
    @Value("${gpt.api.url}")
    private String gptUrl;
    @Value("${gpt.model}")
    private String recommendModel;

    @Bean
    public GptWebClient gptWebClient(){
        return new GptWebClient(gptKey, gptUrl, recommendModel);
    }

}