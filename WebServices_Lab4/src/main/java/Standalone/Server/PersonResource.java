package Standalone.Server;

import java.util.Date;
import java.util.List;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/persons")
@Produces({MediaType.APPLICATION_JSON})
public class PersonResource {

    @GET
    public List<Person> getPersons(@QueryParam("name") String name,
            @QueryParam("name") String surname,
            @QueryParam("name") Date dateOfBirth,
            @QueryParam("name") String sex) {
        List<Person> persons = new PostgreSQLDAO().getPersons(name, surname, dateOfBirth, sex);
        return persons;
    }
}
