package capstone.replyRecoommend.global.auth.converter;

import capstone.replyRecoommend.global.auth.domain.User;
import capstone.replyRecoommend.global.auth.dto.UserDtoRes;

public class UserConverter {

    public static UserDtoRes.infoRes info(User user){
        return UserDtoRes.infoRes.builder()
                .name(user.getName())
                .email(user.getEmail())
                .profileUrl(user.getProfileUrl())
                .build();
    }
}
