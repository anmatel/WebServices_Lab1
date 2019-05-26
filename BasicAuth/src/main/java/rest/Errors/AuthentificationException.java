package rest.Errors;

public class AuthentificationException extends Exception{
    public final static AuthentificationException DEFAULT_INSTANCE = new AuthentificationException("Wrong authentification.");

    public AuthentificationException(String message) {
        super(message);
    }
}
