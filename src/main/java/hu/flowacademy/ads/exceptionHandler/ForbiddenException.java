package hu.flowacademy.ads.exceptionHandler;

public class ForbiddenException extends RuntimeException {
    public ForbiddenException(String message) {
        super(message);
    }
}
