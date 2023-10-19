package org.fdryt.ornamental.domain.user;

import lombok.Getter;

@Getter
public enum Permission {

    FAMILIES_CREATE("create:families"),
    FAMILIES_READ("read:families"),
    FAMILY_DELETE("delete:family"),
    FAMILY_UPDATE("update:family"),
    PLANT_CREATE("create:plant"),
    PLANT_DELETE("delete:plant"),
    PLANTS_READ("read:plants");

    private final String permission;

    Permission(String permission) {
        this.permission = permission;
    }
}
