package org.fdryt.ornamental.security;

import static org.fdryt.ornamental.security.Permission.*;

public enum AppUserPermission {

    PERMIT_NUMBER_ONE(PERMIT_ONE), PERMIT_NUMBER_TWO(PERMIT_TWO), PERMIT_NUMBER_THREE(PERMIT_THREE),
    PERMIT_NUMBER_FOUR(PERMIT_FOUR), PERMIT_NUMBER_FIVE(PERMIT_FIVE), PERMIT_NUMBER_SIX(PERMIT_SIX),
    PERMIT_NUMBER_SEVEN(PERMIT_SEVEN), PERMIT_NUMBER_EIGHT(PERMIT_EIGHT), PERMIT_NUMBER_NINE(PERMIT_NINE),
    PERMIT_NUMBER_TEN(PERMIT_TEN), PERMIT_NUMBER_ELEVEN(PERMIT_ELEVEN);

    private final String permission;

    AppUserPermission(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }
}
