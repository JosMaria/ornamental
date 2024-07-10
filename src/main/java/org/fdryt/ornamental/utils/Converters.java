package org.fdryt.ornamental.utils;

public class Converters {

    public static String buildScientificName(String scientificName, Character discoverer) {
        String scientificNameConverted = firstLetterToCapitalize(scientificName);
        if (scientificNameConverted != null && discoverer != null) {
            scientificNameConverted += " " + Character.toUpperCase(discoverer);
        }

        return scientificNameConverted;
    }

    public static String firstLetterToCapitalize(String word) {
        if (word != null && !word.isEmpty()) {
            return word.substring(0, 1).toUpperCase() + word.substring(1);
        }
        return null;
    }
}
