package capstone.replyRecoommend.auth.handler;

import capstone.replyRecoommend.auth.dto.AuthRequestDTO;
import capstone.replyRecoommend.auth.dto.TokenMapper;
import capstone.replyRecoommend.auth.service.AuthService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.util.Map;

@Slf4j
@RequiredArgsConstructor
public class AuthSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
//    private static final String REDIRECT_URL = "http://localhost:3000";

    private final AuthService authService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
            throws IOException, ServletException {
        OAuth2User oAuth2User = (OAuth2User)authentication.getPrincipal();
        Map<String, Object> attributes = oAuth2User.getAttributes();

        TokenMapper tokenMapper = authService.loginUser(AuthRequestDTO.LoginDTO.builder()
                        .name(attributes.get("name").toString())
                        .email(attributes.get("email").toString())
                        .profileUrl(attributes.get("picture").toString())
                        .build());
        String host = request.getHeader("host");
        host = host.equals("localhost:8080")?"localhost:3000":host;
        response.sendRedirect(makeUrl(host, tokenMapper));
    }

    private String makeUrl(String host, TokenMapper tokens){
        String[] hostAndPort = host.split(":");
        if (hostAndPort.length==2) {
            return UriComponentsBuilder.newInstance()
                    .scheme("https")
                    .host(hostAndPort[0])
                    .port(Integer.parseInt(hostAndPort[1]))
                    .queryParam("accessToken", tokens.getAccessToken())
                    .queryParam("refreshToken", tokens.getRefreshToken())
                    .build(true)
                    .toString();
        }
        else {
            return UriComponentsBuilder.newInstance()
                    .scheme("https")
                    .host(hostAndPort[0])
                    .queryParam("accessToken", tokens.getAccessToken())
                    .queryParam("refreshToken", tokens.getRefreshToken())
                    .build(true)
                    .toString();
        }
    }
}
