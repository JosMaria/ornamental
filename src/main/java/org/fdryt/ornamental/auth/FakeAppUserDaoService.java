package org.fdryt.ornamental.auth;

import com.google.common.collect.Lists;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import static org.fdryt.ornamental.security.AppUserRole.ADMINISTRATOR;
import static org.fdryt.ornamental.security.AppUserRole.ASSISTANT;

@Repository("fake")
@RequiredArgsConstructor
public class FakeAppUserDaoService implements AppUserDao {

    private final PasswordEncoder passwordEncoder;

    @Override
    public Optional<AppUser> findAppUserByUsername(String username) {
        return getAppUsers().stream()
                .filter(appUser -> username.equals(appUser.getUsername()))
                .findFirst();
    }

    private List<AppUser> getAppUsers() {
        return Lists.newArrayList(
                new AppUser(
                        ADMINISTRATOR.getGrantedAuthorities(),
                        "consuelo",
                        passwordEncoder.encode("consuelo17")
                ),
                new AppUser(
                        ASSISTANT.getGrantedAuthorities(),
                        "jose",
                        passwordEncoder.encode("jose17")
                ),
                new AppUser(
                        ASSISTANT.getGrantedAuthorities(),
                        "maria",
                        passwordEncoder.encode("maria17")
                ),
                new AppUser(
                        ASSISTANT.getGrantedAuthorities(),
                        "antornio",
                        passwordEncoder.encode("antonio17")
                )
        );
    }
}
