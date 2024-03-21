package dev.infochem.clilibrary;

public class UnknownCommandException extends RuntimeException {
    public UnknownCommandException(String message) {
        super(message);
    }
    public UnknownCommandException(String message, Throwable cause) {
        super(message, cause);
    }
}
