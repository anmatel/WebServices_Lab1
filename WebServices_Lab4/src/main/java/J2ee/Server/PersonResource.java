package J2ee.Server;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.enterprise.context.RequestScoped;
import javax.sql.DataSource;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@RequestScoped
@Path("/persons")
@Produces({MediaType.APPLICATION_JSON})
public class PersonResource {

    @Resource(lookup = "jdbc/ifmo-ws")
    private DataSource dataSource;

    @GET
    public List<Person> getPersons(
            @QueryParam("name") String name,
            @QueryParam("name") String surname,
            @QueryParam("name") Date dateOfBirth,
            @QueryParam("name") String sex) {
        List<Person> persons = new J2ee.Server.PostgreSQLDAO(getConnection()).getPersons(name, surname, dateOfBirth, sex);
        return persons;
    }

    private Connection getConnection() {
        Connection result = null;
        try {
            result = dataSource.getConnection();
        } catch (SQLException ex) {
            Logger.getLogger(PersonResource.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
}
