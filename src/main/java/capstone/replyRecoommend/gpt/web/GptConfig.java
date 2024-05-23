package capstone.replyRecoommend.gpt.web;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
@Getter
public class GptConfig {
    @Value("${web.gpt.api.key}")
    private String gptKey;
    @Value("${web.gpt.api.url}")
    private String gptUrl;
    @Value("${web.gpt.model}")
    private String recommendModel;

    @Bean
    public GptWebClient gptWebClient(){
        return new GptWebClient(gptKey, gptUrl, recommendModel);
    }

}