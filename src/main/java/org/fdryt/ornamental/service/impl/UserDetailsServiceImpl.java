package org.fdryt.ornamental.service.impl;

import lombok.RequiredArgsConstructor;
import org.fdryt.ornamental.domain.account.Account;
import org.fdryt.ornamental.service.AccountService;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@RequiredArgsConstructor
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final AccountService accountService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account accountObtained = accountService.findByUsername(username);
        Collection<GrantedAuthority> authorities = List.of(new SimpleGrantedAuthority("ROLE_" + accountObtained.getRole().name()));

        return new User(
                accountObtained.getUsername(),
                accountObtained.getPassword(),
                accountObtained.isEnabled(),
                !accountObtained.isExpired(),
                !accountObtained.isCredentialsExpired(),
                !accountObtained.isLocked(), 
                authorities
        );
    }
}
