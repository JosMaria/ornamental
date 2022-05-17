package org.fdryt.ornamental.security.nursery;

import static org.fdryt.ornamental.security.nursery.RoleAndPermission.CREATE_PLANT_AUTHORIZATION;

public enum NurseryUserPermission {

    CREATE_PLANT(CREATE_PLANT_AUTHORIZATION);

    private final String permission;

    NurseryUserPermission(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }
}
