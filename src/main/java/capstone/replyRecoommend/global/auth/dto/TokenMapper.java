package capstone.replyRecoommend.global.auth.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class TokenMapper {
    private String accessToken;
    private String refreshToken;
}
