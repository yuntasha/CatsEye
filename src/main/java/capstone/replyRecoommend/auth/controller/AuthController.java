package capstone.replyRecoommend.auth.controller;

import capstone.replyRecoommend.auth.dto.AuthRequestDTO;
import capstone.replyRecoommend.auth.dto.TokenMapper;
import capstone.replyRecoommend.auth.service.AuthService;
import capstone.replyRecoommend.global.response.SuccessResponse;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {
    private final AuthService authService;

    @Value("${spring.security.oauth2.client.registration.google.redirect-uri}")
    private String url;

    @GetMapping
    public void oauth2Login(HttpServletResponse response) throws IOException {
        response.sendRedirect(url);
    }

    @PostMapping("/reissue")
    public SuccessResponse<TokenMapper> reissue(@Valid @RequestBody AuthRequestDTO.RefreshTokenDTO refreshTokenDTO){
        return SuccessResponse.success(authService.reissue(refreshTokenDTO));
    }
}
