package org.fdryt.ornamental.repository;

import org.fdryt.ornamental.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserJpaRepository extends JpaRepository<User, Integer> {

    Optional<User> findByUsername(String username);
}
