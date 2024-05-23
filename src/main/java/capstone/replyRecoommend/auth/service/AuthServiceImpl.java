package capstone.replyRecoommend.auth.service;

import capstone.replyRecoommend.auth.converter.UserConverter;
import capstone.replyRecoommend.auth.domain.User;
import capstone.replyRecoommend.auth.dto.AuthRequestDTO;
import capstone.replyRecoommend.auth.dto.TokenMapper;
import capstone.replyRecoommend.auth.dto.UserDtoRes;
import capstone.replyRecoommend.auth.repository.UserRepository;
import capstone.replyRecoommend.global.exception.BusinessException;
import capstone.replyRecoommend.global.exception.errorcode.CommonErrorCode;
import capstone.replyRecoommend.security.JwtFactory;
import capstone.replyRecoommend.security.utils.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final JwtFactory jwtFactory;

    @Override
    @Transactional
    public User findUser(Long id){
        Optional<User> optionalUser = userRepository.findById(id);

        return optionalUser.orElse(null);
    }

    @Override
    @Transactional
    public TokenMapper loginUser(AuthRequestDTO.LoginDTO loginDTO){
        User user = loginOrRegisterUser(loginDTO);
        return jwtFactory.generateBothToken(user.getId());
    }


    private User loginOrRegisterUser(AuthRequestDTO.LoginDTO loginDTO){
        Optional<User> findUser = userRepository.findByEmail(loginDTO.getEmail());
        if (findUser.isPresent()) {
            User user = findUser.get();
            if (user.getProfileUrl().isEmpty()) user.updateUser(loginDTO);
            return user;
        }
        else{
            return userRepository.save(User.builder()
                    .name(loginDTO.getName())
                    .email(loginDTO.getEmail())
                    .profileUrl(loginDTO.getProfileUrl())
                    .build());
        }
    }

    @Override
    public TokenMapper reissue(AuthRequestDTO.RefreshTokenDTO refreshTokenDTO){
        return jwtFactory.reissueToken(refreshTokenDTO.getRefreshToken());
    }

    @Override
    public UserDtoRes.infoRes info(Long userId){
        User user = userRepository.findById(userId).orElseThrow(()->new BusinessException(CommonErrorCode.USER_NOT_FOUND));

        return UserConverter.info(user);

    }

}
