package capstone.replyRecoommend.auth.service;

import capstone.replyRecoommend.auth.domain.User;
import capstone.replyRecoommend.auth.dto.AuthRequestDTO;
import capstone.replyRecoommend.auth.dto.TokenMapper;
import capstone.replyRecoommend.auth.dto.UserDtoRes;

public interface AuthService {
    User findUser(Long id);
    TokenMapper loginUser(AuthRequestDTO.LoginDTO loginDTO);
    TokenMapper reissue(AuthRequestDTO.RefreshTokenDTO refreshTokenDTO);

    UserDtoRes.infoRes info(Long userId);
}
