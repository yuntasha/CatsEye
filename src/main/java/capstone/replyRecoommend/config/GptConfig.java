package capstone.replyRecoommend.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

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