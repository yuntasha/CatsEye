package capstone.replyRecoommend.diagnosis.web;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Map;

public class FlaskWebClient {
    private WebClient webClient;

    public FlaskWebClient(String flaskServerUrl){
        this.webClient = WebClient.builder()
                .defaultHeaders(httpHeaders -> {
                    httpHeaders.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
                })
                .baseUrl(flaskServerUrl)
                .build();
    }

    public Map<String, String> flaskReq(Map<String, String> requestPayload) {
        return webClient.post()
                .uri("/upload")
                .bodyValue(requestPayload)
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<Map<String, String>>() {})
                .block();
    }

}
