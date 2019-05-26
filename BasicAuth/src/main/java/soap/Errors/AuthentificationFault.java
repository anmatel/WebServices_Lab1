package soap.Errors;

public class AuthentificationFault {
    private static final String DEFAULT_MESSAGE = "Your authentification failed. ";
    protected String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message + DEFAULT_MESSAGE;
    }

    public static AuthentificationFault defaultInstance(String message) {
        AuthentificationFault fault = new AuthentificationFault();
        fault.message = DEFAULT_MESSAGE + message;
        
        return fault;
    }
}
