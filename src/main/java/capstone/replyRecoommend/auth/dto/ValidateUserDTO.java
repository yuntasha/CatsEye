package capstone.replyRecoommend.auth.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class ValidateUserDTO {
    private Long id;
    private String email;
}
