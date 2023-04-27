package org.fdryt.ornamental.utils;

import static java.lang.String.format;
import static org.apache.commons.lang3.EnumUtils.getEnum;
import static org.apache.commons.lang3.EnumUtils.isValidEnum;

public class Utils {

    private Utils() {}

    public static  <T extends Enum<T>> T convertToEnum(Class<T> enumClass, String type) {
        if (!isValidEnum(enumClass, type)) {
            throw new IllegalArgumentException(format("%s %s does not valid.", enumClass.getName(), type));
        }
        return getEnum(enumClass, type);
    }
}
