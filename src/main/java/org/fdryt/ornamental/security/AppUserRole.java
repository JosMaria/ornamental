package org.fdryt.ornamental.security;

import com.google.common.collect.Sets;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;

import static org.fdryt.ornamental.security.AppUserPermission.*;

public enum AppUserRole {

    ADMINISTRATOR(Sets.newHashSet(PERMIT_NUMBER_ONE, PERMIT_NUMBER_TWO, PERMIT_NUMBER_THREE,
            PERMIT_NUMBER_FOUR, PERMIT_NUMBER_FIVE, PERMIT_NUMBER_SIX, PERMIT_NUMBER_SEVEN)),

    ASSISTANT(Sets.newHashSet(PERMIT_NUMBER_EIGHT, PERMIT_NUMBER_NINE, PERMIT_NUMBER_TEN, PERMIT_NUMBER_ELEVEN));

    private final Set<AppUserPermission> permissions;

    AppUserRole(Set<AppUserPermission> permissions) {
        this.permissions = permissions;
    }

    public Set<AppUserPermission> getPermissions() {
        return permissions;
    }

    public Set<SimpleGrantedAuthority> getGrantedAuthorities() {
        Set<SimpleGrantedAuthority> permissions = getPermissions().stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                .collect(Collectors.toSet());

        permissions.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
        return permissions;
    }
}
