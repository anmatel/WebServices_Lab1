package soap.Errors;

import javax.xml.ws.WebFault;

@WebFault(faultBean = "soap.Errors.AuthentificationException")
public class AuthentificationException extends Exception{
    private final AuthentificationFault fault;
    
    public AuthentificationException(String message, AuthentificationFault fault) {
        super(message);
        this.fault = fault;
    }
    public AuthentificationException(String message, AuthentificationFault fault, Throwable cause) {
        super(message, cause);
        this.fault = fault;
    }
    public AuthentificationFault getFaultInfo() {
        return fault;
    }
}
