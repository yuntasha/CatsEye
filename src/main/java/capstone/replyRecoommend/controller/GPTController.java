package capstone.replyRecoommend.controller;

import capstone.replyRecoommend.dto.GPTRequest;
import capstone.replyRecoommend.dto.GPTResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.reactive.function.client.WebClient;


@Controller
@RequiredArgsConstructor
public class GPTController {

    @Value("${gpt.model}")
    private String model;

    private final WebClient webClient;


    @GetMapping("/chat")
    public ResponseEntity<GPTResponse> chat(@RequestParam String prompt){

        GPTRequest request = new GPTRequest(model,prompt+" tone: sad");

        return webClient.post()
                .bodyValue(request)
                .retrieve()
                .toEntity(GPTResponse.class).block();
    }
}