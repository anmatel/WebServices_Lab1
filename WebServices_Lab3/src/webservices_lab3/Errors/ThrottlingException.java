package webservices_lab3.Errors;

import javax.xml.ws.WebFault;

@WebFault(faultBean = "webservices_lab3.Errors.ThrottlingFault")
public class ThrottlingException extends Exception {
    private final ThrottlingFault fault;
    
    public ThrottlingException(ThrottlingFault fault) {
        this.fault = fault;
    }
    
    public ThrottlingException(String message, ThrottlingFault fault) {
        super(message);
        this.fault = fault;
    }

    public ThrottlingException(String message, ThrottlingFault fault, Throwable cause) {
        super(message, cause);
        this.fault = fault;
    }

    public ThrottlingFault getFaultInfo() {
        return fault;
    }
}
