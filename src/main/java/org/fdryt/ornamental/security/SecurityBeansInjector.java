package org.fdryt.ornamental.security;

import lombok.RequiredArgsConstructor;
import org.fdryt.ornamental.repository.UserJpaRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@RequiredArgsConstructor
public class SecurityBeansInjector {

    private final UserJpaRepository userJpaRepository;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return username -> userJpaRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(String.format("Username: %s does not exists", username)));
    }
}
