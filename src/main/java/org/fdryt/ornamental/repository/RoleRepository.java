package org.fdryt.ornamental.repository;

import org.fdryt.ornamental.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    @Query("SELECT role " +
            "FROM Role role " +
            "WHERE role.name = :name")
    Optional<Role> findByName(@Param(":name") String name);

}
