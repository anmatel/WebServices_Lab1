package webservices_lab6.Errors;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class IllegalEnteredDataExceptionMapper implements ExceptionMapper<IllegalEnteredDataException> {

    @Override
    public Response toResponse(IllegalEnteredDataException e) {
        return Response.status(Status.BAD_REQUEST).entity(e.getMessage()).build();
    }
}
