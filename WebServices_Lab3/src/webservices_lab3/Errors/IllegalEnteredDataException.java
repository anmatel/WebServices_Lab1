package webservices_lab3.Errors;

import javax.xml.ws.WebFault;

@WebFault(faultBean = "webservices_lab3.Errors.PersonServiceFault")
public class IllegalEnteredDataException extends Exception {
    private final PersonServiceFault fault;

    public IllegalEnteredDataException(String message, PersonServiceFault fault) {
        super(message);
        this.fault = fault;
    }

    public IllegalEnteredDataException(String message, PersonServiceFault fault, Throwable cause) {
        super(message, cause);
        this.fault = fault;
    }

    public PersonServiceFault getFaultInfo() {
        return fault;
    }
}
