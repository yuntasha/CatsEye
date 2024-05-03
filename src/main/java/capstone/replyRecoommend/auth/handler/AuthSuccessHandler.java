package capstone.replyRecoommend.auth.handler;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NoArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;

import java.io.IOException;
import java.util.Map;

@NoArgsConstructor
public class AuthSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
    private static final String TOKEN = "accessToken";
    private static final String REFRESH_TOKEN = "refreshToken";
//    private static final String REDIRECT_URL = "http://localhost:3000";



    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
            throws IOException, ServletException {
        OAuth2User oAuth2User = (OAuth2User)authentication.getPrincipal();

    }
}
