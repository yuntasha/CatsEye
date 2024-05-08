package capstone.replyRecoommend.gpt.service;

import capstone.replyRecoommend.gpt.dto.ChatDtoReq;
import capstone.replyRecoommend.gpt.dto.ChatDtoRes;

public interface GptService {

    ChatDtoRes.ChatAnwDtoRes initChat(Long userId, ChatDtoReq.initChatReq chatDtoReq);

    ChatDtoRes.ChatAnwDtoRes chat(Long userId, ChatDtoReq.chatReq request);

    void removeChat(Long userId);
}
