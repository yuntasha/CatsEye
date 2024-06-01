package capstone.replyRecoommend.global.auth.service;

import capstone.replyRecoommend.global.auth.domain.User;
import capstone.replyRecoommend.global.auth.dto.AuthRequestDTO;
import capstone.replyRecoommend.global.auth.dto.TokenMapper;
import capstone.replyRecoommend.global.auth.dto.UserDtoRes;

public interface AuthService {
    User findUser(Long id);
    TokenMapper loginUser(AuthRequestDTO.LoginDTO loginDTO);
    TokenMapper reissue(AuthRequestDTO.RefreshTokenDTO refreshTokenDTO);

    UserDtoRes.infoRes info(Long userId);
}
