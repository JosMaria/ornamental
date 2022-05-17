package org.fdryt.ornamental.security;

/*
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import static org.fdryt.ornamental.security.nursery.NurseryUserRole.ASSISTANT;
*/

/*
@EnableWebSecurity
@RequiredArgsConstructor
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    private final PasswordEncoder passwordEncoder;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .anyRequest().authenticated()
                .and()
                .httpBasic();
    }

    @Bean
    @Override
    protected UserDetailsService userDetailsService() {
        UserDetails jose = User.withUsername("jose")
                .password(passwordEncoder.encode("jose17"))
//                .roles(ASSISTANT.name())
                .authorities(ASSISTANT.getGrantedAuthorities())
                .build();

        UserDetails maria = User.withUsername("maria")
                .password(passwordEncoder.encode("maria17"))
                .roles(ASSISTANT.name())
                //.authorities(ASSISTANT.getGrantedAuthorities())
                .build();

        UserDetails consuelo = User.withUsername("consuelo")
                .password(passwordEncoder.encode("consuelo17"))
                //.roles(ASSISTANT.name())
                .authorities(ASSISTANT.getGrantedAuthorities())
                .build();

        UserDetails david = User.withUsername("david")
                .password(passwordEncoder.encode("david17"))
                //.roles(ASSISTANT.name())
                .authorities(ASSISTANT.getGrantedAuthorities())
                .build();

        return new InMemoryUserDetailsManager(jose, maria, consuelo, david);
    }
}
*/
