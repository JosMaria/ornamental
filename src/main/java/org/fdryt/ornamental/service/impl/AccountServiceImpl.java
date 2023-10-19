package org.fdryt.ornamental.service.impl;

import lombok.RequiredArgsConstructor;
import org.fdryt.ornamental.domain.account.Account;
import org.fdryt.ornamental.domain.account.Role;
import org.fdryt.ornamental.repository.AccountJpaRepository;
import org.fdryt.ornamental.service.AccountService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final AccountJpaRepository accountJpaRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Account createAccount(Account account) {
        account.setPassword(passwordEncoder.encode(account.getPassword()));
        account.setRole(Role.ASSISTANT);
        return accountJpaRepository.save(account);
    }

    @Override
    public Account findByUsername(String username) {
        return accountJpaRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Username %s not found.".formatted(username)));
    }

    @Override
    public List<Account> getAllAccounts() {
        return accountJpaRepository.findAll();
    }
}
