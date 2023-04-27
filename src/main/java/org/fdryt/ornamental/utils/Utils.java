package org.fdryt.ornamental.utils;

import org.fdryt.ornamental.problem.exception.EnumNotPresentException;

import static org.apache.commons.lang3.EnumUtils.getEnum;
import static org.apache.commons.lang3.EnumUtils.isValidEnum;

public class Utils {

    private Utils() {}

    public static  <T extends Enum<T>> T convertToEnum(Class<T> enumClass, String type) {
        if (!isValidEnum(enumClass, type)) {
            throw new EnumNotPresentException(enumClass, type);
        }
        return getEnum(enumClass, type);
    }
}
