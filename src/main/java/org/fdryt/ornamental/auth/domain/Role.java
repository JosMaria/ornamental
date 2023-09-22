/*package org.fdryt.ornamental.auth.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static org.fdryt.ornamental.auth.domain.Permission.*;

@RequiredArgsConstructor
public enum Role {

    ADMINISTRATOR(
        Set.of(
            PLANT_CREATE,
            PLANT_DELETE,
            NEWS_CREATE,
            NEWS_DELETE,
            NEWS_UPDATE,
            NEWS_FIELDS_UPDATE,
            FAMILY_CREATE,
            FAMILY_READ
        )
    ),

    ASSISTANT(
        Set.of(
            PLANT_CREATE,
            NEWS_CREATE,
            FAMILY_CREATE,
            FAMILY_READ
        )
    );

    @Getter
    private final Set<Permission> permissions;

    public List<SimpleGrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority> authorities = getPermissions().stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                .collect(Collectors.toCollection(ArrayList::new));
        authorities.add(new SimpleGrantedAuthority("ROLE_" + this.name()));

        return authorities;
    }
}
*/