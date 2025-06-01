package model.exceptions;

/**
 * Thrown to indicate a simulated failure in the external inventory system.
 */
public class DatabaseFailureException extends Exception {
    public DatabaseFailureException(String message) {
        super(message);
    }
}
