package rest.server;

import java.util.Date;
import java.util.List;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import rest.Client.WebClient;
import rest.Errors.*;

@Path("/persons")
@Produces({MediaType.APPLICATION_JSON})
public class PersonResource {
    
    private void checkAuth(String authString) throws AuthentificationException{
        if (authString.isEmpty())
            throw AuthentificationException.DEFAULT_INSTANCE;
        else 
            for (String authStr : authString.split(":")){
                if (!(authStr.equals(WebClient.LOGIN) & authStr.equals(WebClient.PASSWORD)))
                    throw AuthentificationException.DEFAULT_INSTANCE;
            }
    }

    @GET
    @Path("/get")
    public List<Person> getPersons(
            @HeaderParam("authorization") String authString,
            @QueryParam("name") String name,
            @QueryParam("name") String surname,
            @QueryParam("name") Date dateOfBirth,
            @QueryParam("name") String sex) throws IllegalPersonSexValueException, IllegalEnteredDataException, AuthentificationException {
        checkAuth(authString);
        List<Person> persons = new PostgreSQLDAO().getPersons(name, surname, dateOfBirth, sex);
        return persons;
    }

    @POST
    @Path("/create")
    public Integer createPerson(
            @HeaderParam("authorization") String authString,
            @QueryParam("name") String name,
            @QueryParam("name") String surname,
            @QueryParam("name") Date dateOfBirth,
            @QueryParam("name") String sex) throws  IllegalEnteredDataException, IllegalPersonSexValueException, AuthentificationException {
        checkAuth(authString);
        int id = new PostgreSQLDAO().createPerson(name, surname, dateOfBirth, sex);
        return id;
    }
    
    @POST
    @Path("/update")
    public String updatePerson(
            @HeaderParam("authorization") String authString,
            @QueryParam("id") int id,
            @QueryParam("name") String name,
            @QueryParam("name") String surname,
            @QueryParam("name") Date dateOfBirth,
            @QueryParam("name") String sex) throws  IllegalEnteredDataException, IllegalPersonSexValueException, AuthentificationException  {
        checkAuth(authString);
        String status = new PostgreSQLDAO().updatePerson(id, name, surname, dateOfBirth, sex);
        return status;
    }
    
    @DELETE
    @Path("/delete")
    public String deletePerson(@HeaderParam("authorization") String authString, @QueryParam("id") int id) throws AuthentificationException {
        checkAuth(authString);
        String status = new PostgreSQLDAO().deletePerson(id);
        return status;
    }
}