package capstone.replyRecoommend.auth.controller;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class AuthController {
    @GetMapping("/login")
    public void oauth2Login(HttpServletResponse response) throws IOException {
        response.sendRedirect("http://localhost:8080/oauth2/authorization/google");
    }
}
