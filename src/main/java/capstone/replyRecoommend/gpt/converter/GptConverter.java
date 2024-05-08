package capstone.replyRecoommend.gpt.converter;

import capstone.replyRecoommend.gpt.dto.ChatDtoRes;
public class GptConverter {
    public static ChatDtoRes.ChatAnwDtoRes answer(String content){
        return ChatDtoRes.ChatAnwDtoRes.builder()
                .message(content)
                .build();
    }
}
