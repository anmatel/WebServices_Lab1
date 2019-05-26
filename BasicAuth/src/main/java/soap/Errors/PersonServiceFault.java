package soap.Errors;

public class PersonServiceFault {

    private static final String DEFAULT_MESSAGE = " cannot be null or empty";
    protected String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message.concat(DEFAULT_MESSAGE);
    }

    public static PersonServiceFault defaultInstance(String personParameter) {
        PersonServiceFault fault = new PersonServiceFault();
        fault.message = personParameter.concat(DEFAULT_MESSAGE);
        return fault;
    }
}
