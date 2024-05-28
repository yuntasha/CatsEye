package capstone.replyRecoommend.diagnosis.web;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
@Getter
public class FlaskConfig {

    @Value("${web.flask.server.url}")
    private String flaskServerUrl;
    @Bean
    public FlaskWebClient FlaskWebClient(){
        return new FlaskWebClient(flaskServerUrl);
    }
}
