package org.fdryt.ornamental.repository;

import org.fdryt.ornamental.domain.account.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountJpaRepository extends JpaRepository<Account, Integer> {

    Optional<Account> findByUsername(String username);
}
