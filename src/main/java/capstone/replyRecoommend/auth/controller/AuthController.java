package capstone.replyRecoommend.auth.controller;

import capstone.replyRecoommend.auth.dto.AuthRequestDTO;
import capstone.replyRecoommend.auth.dto.TokenMapper;
import capstone.replyRecoommend.auth.service.AuthService;
import capstone.replyRecoommend.global.response.SuccessResponse;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {
    private final AuthService authService;

    @GetMapping
    public void oauth2Login(HttpServletResponse response) throws IOException {
        response.sendRedirect("http://13.209.162.245:8080/oauth2/authorization/google");
    }

    @PostMapping("/reissue")
    public SuccessResponse<TokenMapper> reissue(@Valid @RequestBody AuthRequestDTO.RefreshTokenDTO refreshTokenDTO){
        return SuccessResponse.success(authService.reissue(refreshTokenDTO));
    }
}
