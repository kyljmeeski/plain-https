package com.kyljmeeski.plainhttps;

/**
 * Exception thrown when a required field is missing during deserialization.
 */
public class MissingFieldException extends Exception {

    /**
     * Constructs a new MissingFieldException with the specified error message.
     *
     * @param message the error message
     */
    public MissingFieldException(String message) {
        super(message);
    }

}
