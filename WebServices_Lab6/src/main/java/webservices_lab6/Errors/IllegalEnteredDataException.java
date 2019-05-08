package webservices_lab6.Errors;

public class IllegalEnteredDataException extends Exception {

    public static IllegalEnteredDataException DEFAULT_INSTANCE = new IllegalEnteredDataException("input data cannot be null or empty");

    public IllegalEnteredDataException(String message) {
        super(message);
    }
}
