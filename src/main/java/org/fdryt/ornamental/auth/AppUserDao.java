package org.fdryt.ornamental.auth;

import java.util.Optional;

public interface AppUserDao {

    Optional<AppUser> findAppUserByUsername(String username);
}
