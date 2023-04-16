package org.fdryt.ornamental.domain;

public enum ClassificationByUtility {

    ORNAMENTAL("ornamental"), FOREST("forestal"), INDUSTRIAL("industrial"),
    ALIMENTARY("alimenticia"), MEDICINAL("medicinal"), EXOTIC("exotica"),
    CACTUS("cactus"), FRUITFUL("frutal"), GRASS("crasa"), SUCCULENT("suculenta");

    private final String type;

    ClassificationByUtility(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
