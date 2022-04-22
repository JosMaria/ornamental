package org.fdryt.ornamental.repository;

import org.fdryt.ornamental.domain.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<AppUser, Long> {

    @Query("SELECT user " +
            "FROM AppUser user " +
            "WHERE user.username = :username")
    Optional<AppUser> findByUsername(@Param("username") String username);
}
