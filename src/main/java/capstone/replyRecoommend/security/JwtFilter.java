package capstone.replyRecoommend.security;

import capstone.replyRecoommend.auth.domain.User;
import capstone.replyRecoommend.auth.service.AuthService;
import capstone.replyRecoommend.security.utils.JwtUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Base64;
import java.util.List;

@Slf4j
public class JwtFilter extends OncePerRequestFilter {

    private final AuthService userService;
    private final String accessKey;

    public JwtFilter(AuthService userService, String accessKey) {
        this.userService = userService;
        this.accessKey =  Base64.getEncoder().encodeToString(accessKey.getBytes());
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        final String authentication = request.getHeader(HttpHeaders.AUTHORIZATION);
        System.out.println("authentication = " + authentication);


        if (authentication != null && authentication.startsWith("Bearer ")){
            // 토큰 꺼내기
            String token = authentication.split(" ")[1];
            if (!JwtUtil.isExpired(token, accessKey, request)){
                Long id = JwtUtil.getId(token, accessKey);
                User user = userService.findUser(id);

                UsernamePasswordAuthenticationToken authenticationToken =
                        new UsernamePasswordAuthenticationToken(id, null, List.of(new SimpleGrantedAuthority(user.getRole().getValue()))); // 유저 디테일을 권한 넣음

                authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            }
        }
        filterChain.doFilter(request, response);
        return;
    }
}
