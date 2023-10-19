package org.fdryt.ornamental.domain.user;

import lombok.Getter;

import java.util.Set;

import static org.fdryt.ornamental.domain.user.Permission.*;

@Getter
public enum Role {

    ADMINISTRATOR(
        Set.of(
            FAMILIES_CREATE,
            FAMILIES_READ,
            FAMILY_DELETE,
            FAMILY_UPDATE,
            PLANT_CREATE,
            PLANT_DELETE,
            PLANTS_READ
        )
    ),

    ASSISTANT(
        Set.of(
            FAMILIES_READ,
            PLANTS_READ
        )
    );

    private final Set<Permission> permissions;

    Role(Set<Permission> permissions) {
        this.permissions = permissions;
    }
}
