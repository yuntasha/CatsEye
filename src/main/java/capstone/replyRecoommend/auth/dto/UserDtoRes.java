package capstone.replyRecoommend.auth.dto;

import lombok.Builder;
import lombok.Getter;

public class UserDtoRes {

    @Builder
    @Getter
    public static class infoRes{
        private String name;
        private String email;
        private String profileUrl;
    }
}
