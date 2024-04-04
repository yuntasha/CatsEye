package capstone.replyRecoommend.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class GPTConfig {
    @Value("${gpt.api.key}")
    private String apiKey;
    @Value("${gpt.api.url}")
    private String url;

    @Bean
    public WebClient webClient(){
        return WebClient.builder()
                .defaultHeaders(httpHeaders -> {
                    httpHeaders.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
                    httpHeaders.add(HttpHeaders.AUTHORIZATION, "Bearer "+ apiKey);
                })
                .baseUrl(url)
                .build();
    }

}