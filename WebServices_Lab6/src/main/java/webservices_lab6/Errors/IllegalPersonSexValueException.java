package webservices_lab6.Errors;

public class IllegalPersonSexValueException extends Exception {
    public static IllegalPersonSexValueException DEFAULT_INSTANCE = new IllegalPersonSexValueException("sex could be only 'male' or 'female'");

    public IllegalPersonSexValueException(String message) {
        super(message);
    }
}
