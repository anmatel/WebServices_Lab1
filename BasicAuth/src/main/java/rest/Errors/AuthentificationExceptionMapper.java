package rest.Errors;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

public class AuthentificationExceptionMapper implements ExceptionMapper<IllegalEnteredDataException>{
    @Override
    public Response toResponse(IllegalEnteredDataException e) {
        return Response.status(Response.Status.UNAUTHORIZED).entity(e.getMessage()).build();
    }
}
