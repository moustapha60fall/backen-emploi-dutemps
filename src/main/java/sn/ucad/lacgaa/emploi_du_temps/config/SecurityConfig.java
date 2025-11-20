package sn.ucad.lacgaa.emploi_du_temps.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.web.authentication.BearerTokenAuthenticationFilter;
import org.springframework.security.web.SecurityFilterChain;
import sn.ucad.lacgaa.emploi_du_temps.security.JwtUserSyncFilter;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
public class SecurityConfig {

    private final JwtUserSyncFilter jwtUserSyncFilter;

    public SecurityConfig(JwtUserSyncFilter jwtUserSyncFilter) {
        this.jwtUserSyncFilter = jwtUserSyncFilter;
    }

    @Bean
    public SecurityFilterChain configure(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable())
                .cors(withDefaults())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .oauth2ResourceServer(oauth2 -> oauth2.jwt(jwt -> jwt.jwtAuthenticationConverter(jwtAuthenticationConverter())));

        http.headers(headers -> headers
                .contentSecurityPolicy("frame-src 'self' https://www.google.com https://www.recaptcha.net;")
                .and()
                .frameOptions()
                .sameOrigin());

        http.authorizeHttpRequests(auth -> auth
                .requestMatchers("/api/v1/**", "/error").permitAll()
                .anyRequest().authenticated());

        http.addFilterAfter(jwtUserSyncFilter, BearerTokenAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public JwtAuthenticationConverter jwtAuthenticationConverter() {
        return new JwtAuthenticationConverter();
    }
}