package capstone.replyRecoommend.gpt.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class GptRequest {
    private String model;
    private List<Message> messages = new ArrayList<>();
    private float temperature;
    private int maxTokens;

    public GptRequest(String model, String msg, Integer maxTokens) {
        this.model = model;
        this.messages.add(new Message("user", msg));
        this.maxTokens = maxTokens;
        this.temperature = 0.7f;
    }
}
