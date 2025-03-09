package dev.warriorg.retro.exception;

public class RetroBoardNotFoundException extends RuntimeException{

    public RetroBoardNotFoundException() {
        super("RetroBoard Not Found");
    }

    public RetroBoardNotFoundException(String message) {
        super(String.format("RetroBoard not found: {}", message));
    }

    public RetroBoardNotFoundException(String message, Throwable cause) {
        super(String.format("RetroBoard not found: %s", message), cause);
    }

}
