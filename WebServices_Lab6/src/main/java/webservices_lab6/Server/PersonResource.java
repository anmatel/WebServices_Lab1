package webservices_lab6.Server;

import java.util.Date;
import java.util.List;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import webservices_lab6.Errors.*;

@Path("/persons")
@Produces({MediaType.APPLICATION_JSON})
public class PersonResource {

    @GET
    @Path("/get")
    public List<Person> getPersons(
            @QueryParam("name") String name,
            @QueryParam("name") String surname,
            @QueryParam("name") Date dateOfBirth,
            @QueryParam("name") String sex) throws IllegalPersonSexValueException, IllegalEnteredDataException {
        List<Person> persons = new PostgreSQLDAO().getPersons(name, surname, dateOfBirth, sex);
        return persons;
    }

    @POST
    @Path("/create")
    public Integer createPerson(
            @QueryParam("name") String name,
            @QueryParam("name") String surname,
            @QueryParam("name") Date dateOfBirth,
            @QueryParam("name") String sex) throws  IllegalEnteredDataException, IllegalPersonSexValueException {
        int id = new PostgreSQLDAO().createPerson(name, surname, dateOfBirth, sex);
        return id;
    }
    
    @POST
    @Path("/update")
    public String updatePerson(
            @QueryParam("id") int id,
            @QueryParam("name") String name,
            @QueryParam("name") String surname,
            @QueryParam("name") Date dateOfBirth,
            @QueryParam("name") String sex) throws  IllegalEnteredDataException, IllegalPersonSexValueException  {
        String status = new PostgreSQLDAO().updatePerson(id, name, surname, dateOfBirth, sex);
        return status;
    }
    
    @DELETE
    @Path("/delete")
    public String deletePerson(@QueryParam("id") int id) {
        String status = new PostgreSQLDAO().deletePerson(id);
        return status;
    }
}
