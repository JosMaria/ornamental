package org.fdryt.ornamental.security;

import com.google.common.collect.Sets;

import java.util.Set;

public enum AppUserRole {

    ADMINISTRATOR(Sets.newHashSet(AppUserPermission.ADMINISTRATOR_CREATE)),
    ASSISTANT(Sets.newHashSet());

    private final Set<AppUserPermission> permissions;

    AppUserRole(Set<AppUserPermission> permissions) {
        this.permissions = permissions;
    }

    public Set<AppUserPermission> getPermissions() {
        return permissions;
    }
}
