package dev.warriorg.retro.exception;

public class CardNotFoundException extends RuntimeException {
    public CardNotFoundException() {
        super("Card Not Found");
    }

    public CardNotFoundException(String message) {
        super(String.format("Card not found: %s", message));
    }

    public CardNotFoundException(String message, Throwable cause) {
        super(String.format("Card not found: %s", message), cause);
    }
}
