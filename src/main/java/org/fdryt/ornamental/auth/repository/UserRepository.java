/*package org.fdryt.ornamental.auth.repository;

import org.fdryt.ornamental.auth.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User> findByUsername(String username);

    @Query("""
            SELECT CASE WHEN COUNT(u) > 0
                THEN TRUE
                ELSE FALSE END
            FROM User u
            WHERE u.username = :username""")
    boolean existsByUsername(@Param("username") String username);
}
*/