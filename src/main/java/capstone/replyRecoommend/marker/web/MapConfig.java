package capstone.replyRecoommend.marker.web;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
@Getter
public class MapConfig {
    @Value("${web.map.api.key}")
    private String gptKey;
    @Value("${web.map.api.url}")
    private String gptUrl;

    @Bean
    public MapWebClient mapWebClient(){
        return new MapWebClient(gptKey, gptUrl);
    }

}