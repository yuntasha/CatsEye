package capstone.replyRecoommend.gpt.web.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class GptRequest {
    private String model;
    private List<Message> messages;
    private float temperature;
    private int maxTokens;

    public GptRequest(String model, Integer maxTokens) {
        this.model = model;
        this.messages = new ArrayList<>();
        this.maxTokens = maxTokens;
        this.temperature = 0.7f;
    }

    public void addMessage(String role, String message){
        this.messages.add(new Message(role,message));
    }

    public static GptRequest of(String model,int maxTokens){
        return new GptRequest(model,maxTokens);
    }

}
