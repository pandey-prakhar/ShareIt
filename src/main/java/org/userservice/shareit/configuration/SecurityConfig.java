package org.userservice.shareit.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;

@Configuration
public class SecurityConfig {

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http.authorizeHttpRequests(auth -> {
                    auth.requestMatchers("/files/login","/files/download/{id}","/files/share/{id}","/styles/**").permitAll();
                    auth.anyRequest().authenticated();

                })
                .oauth2Login(oauth2Login -> oauth2Login
                        .loginPage("/files/login") // Custom login page
                        .successHandler(customSuccessHandler())
                )
                .csrf(csrf -> csrf.disable()); // Disable CSRF for simplicity; use it in production.

        return http.build();
    }

    public AuthenticationSuccessHandler customSuccessHandler() {
        SimpleUrlAuthenticationSuccessHandler successHandler = new SimpleUrlAuthenticationSuccessHandler();
        successHandler.setDefaultTargetUrl("/files/home"); // Redirect to this URL after successful login
        return successHandler;
    }
}
