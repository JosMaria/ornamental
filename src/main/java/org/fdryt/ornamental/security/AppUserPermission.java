package org.fdryt.ornamental.security;

public enum AppUserPermission {

    ADMINISTRATOR_CREATE("administrator:create");

    private final String permission;

    AppUserPermission(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }
}
