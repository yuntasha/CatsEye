package capstone.replyRecoommend.auth.service;

import capstone.replyRecoommend.auth.domain.User;
import capstone.replyRecoommend.auth.dto.ValidateUserDTO;

public interface UserService {
    User findUser(Long id);
}
