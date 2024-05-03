package capstone.replyRecoommend.security;

import capstone.replyRecoommend.auth.domain.User;
import capstone.replyRecoommend.auth.dto.ValidateUserDTO;
import capstone.replyRecoommend.auth.service.UserService;
import capstone.replyRecoommend.security.utils.JwtUtil;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
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
import java.util.List;

@Slf4j
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {

    private final UserService userService;

    private final String secretKey;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        final String authentication = request.getHeader(HttpHeaders.AUTHORIZATION);
        System.out.println("authentication = " + authentication);

        // 토큰을 보내지 않으면 block
        if (authentication == null || !authentication.startsWith("Bearer ")){
            log.info("authentication을 잘못 보냈습니다.");
            filterChain.doFilter(request, response);
            return;
        }

        // 토큰 꺼내기
        String token = authentication.split(" ")[1];

        // Token Expired되었는지 여부
        try {
            if (JwtUtil.isExpired(token, secretKey)){
                log.info("토큰이 만료되었습니다.");
                filterChain.doFilter(request, response);
                return;
            }
        } catch (ExpiredJwtException e) {
            throw new RuntimeException(e);
        } catch (UnsupportedJwtException e){
            throw new RuntimeException(e);
        } catch (MalformedJwtException e){
            throw new RuntimeException(e);
        } catch (SignatureException e){
            throw new RuntimeException(e);
        } catch (IllegalArgumentException e){
            throw new RuntimeException(e);
        }

        // UserName 토큰에서 꺼내기
        Long id = JwtUtil.getId(token, secretKey);

        User user = userService.findUser(id);

        // 권한 부여
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(user, null, List.of(new SimpleGrantedAuthority(user.getRole().getValue()))); // 유저 디테일을 권한 넣음
        // Detail을 넣어줍니다
        authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        filterChain.doFilter(request, response);
    }
}
