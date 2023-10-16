package org.fdryt.ornamental.service;

import org.fdryt.ornamental.domain.account.Account;

import java.util.List;

public interface AccountService {

    Account createAccount(Account account);
    Account findByUsername(String username);
    List<Account> getAllAccounts();
}
