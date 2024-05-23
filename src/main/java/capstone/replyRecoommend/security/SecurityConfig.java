package capstone.replyRecoommend.security;

import capstone.replyRecoommend.auth.handler.AuthSuccessHandler;
import capstone.replyRecoommend.auth.repository.UserRepository;
import capstone.replyRecoommend.auth.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity(debug = true)
@RequiredArgsConstructor
public class SecurityConfig {

    private final AuthService userService;
    @Value("${jwt.access}")
    private String accessKey;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{
        return httpSecurity
                .httpBasic(AbstractHttpConfigurer::disable)  // 인증을 UI로 할 것이 아니라서 disable을 한 것
                .csrf(AbstractHttpConfigurer::disable) // CSRF도 같음
                .cors(AbstractHttpConfigurer::disable) // CORS 삭젯
                .formLogin(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("oauth2/authorization/**", "/api/v1/auth/**", "/api/v1/hospital/**").permitAll()
                        .anyRequest().authenticated())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(new JwtFilter(userService, accessKey), UsernamePasswordAuthenticationFilter.class)
                .exceptionHandling(e -> e
                        .authenticationEntryPoint(new JwtAuthenticationEntryPoint())
                        .accessDeniedHandler(new JwtAccessDeniedHandler()))
                .oauth2Login(oAuth -> oAuth
                        .successHandler(new AuthSuccessHandler(userService)))
                .build();
    }
}
