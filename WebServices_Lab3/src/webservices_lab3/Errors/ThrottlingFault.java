package webservices_lab3.Errors;

public class ThrottlingFault {

    private static final String DEFAULT_MESSAGE = "Throttling exception. Too many requests.";
    protected String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message.concat(DEFAULT_MESSAGE);
    }

    public static ThrottlingFault defaultInstance() {
        ThrottlingFault fault = new ThrottlingFault();
        fault.message = DEFAULT_MESSAGE;
        return fault;
    }
}
