package org.fdryt.ornamental.utils;

public final class Preconditions {

    private Preconditions() {}

    /**
     * It verifies if the expression meets the condition. If it does not, then it throws an exception.
     *
     * @param expression the required expression evaluated in the client.
     * @param errorMessage the error message to set if it does not meet the condition.
     * @throws IllegalArgumentException when it does not meet the condition.
     */
    public static void checkArgument(boolean expression, String errorMessage) {
        // TODO: create PreconditionsException class
        if (!expression) {
            throw new IllegalArgumentException(errorMessage);
        }
    }
}
