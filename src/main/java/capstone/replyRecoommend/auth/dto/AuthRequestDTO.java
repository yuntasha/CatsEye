package capstone.replyRecoommend.auth.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

public class AuthRequestDTO {

    @Getter
    @Builder
    @AllArgsConstructor
    public static class LoginDTO{
        private String name;
        private String email;
    }

    @Getter
    public static class RefreshTokenDTO{
        @NotNull
        @NotBlank
        private String refreshToken;
    }
}
