package capstone.replyRecoommend.auth.service;

import capstone.replyRecoommend.auth.domain.User;
import capstone.replyRecoommend.auth.dto.ValidateUserDTO;
import capstone.replyRecoommend.auth.repository.UserRepository;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;

    public User findUser(Long id){
        Optional<User> optionalUser = userRepository.findById(id);

        return optionalUser.orElse(null);
    }
}
