package org.fdryt.ornamental.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import static org.fdryt.ornamental.security.AppUserRole.ADMINISTRATOR;
import static org.fdryt.ornamental.security.AppUserRole.ASSISTANT;

@RequiredArgsConstructor
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    private final PasswordEncoder passwordEncoder;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                .anyRequest().authenticated()
                .and()
                .httpBasic();
    }

    @Bean
    @Override
    protected UserDetailsService userDetailsService() {
        UserDetails consuelo = User.withUsername("consuelo")
                .password(passwordEncoder.encode("consuelo17"))
                .authorities(ADMINISTRATOR.getGrantedAuthorities())
                .build();

        UserDetails jose = User.withUsername("jose")
                .password(passwordEncoder.encode("jose17"))
                .authorities(ASSISTANT.getGrantedAuthorities())
                .build();

        UserDetails maria = User.withUsername("maria")
                .password(passwordEncoder.encode("maria17"))
                .authorities(ASSISTANT.getGrantedAuthorities())
                .build();

        UserDetails antonio = User.withUsername("antonio")
                .password(passwordEncoder.encode("antonio17"))
                .authorities(ASSISTANT.getGrantedAuthorities())
                .build();
        return new InMemoryUserDetailsManager(consuelo, jose, maria, antonio);
    }
}
