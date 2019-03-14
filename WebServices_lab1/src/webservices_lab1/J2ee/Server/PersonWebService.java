package webservices_lab1.J2ee.Server;

import java.util.List;
import javax.jws.WebMethod;
import javax.jws.WebService; 
 
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.sql.DataSource;

@WebService(serviceName = "PersonService")
public class PersonWebService {
    @Resource(lookup = "jdbc/wst")
    private DataSource dataSource;
    @WebMethod(operationName = "getPersons")
    public List<webservices_lab1.Server.Person> getPersons(Person person) throws SQLException {
        PostgreSQLDAO dao = new PostgreSQLDAO(getConnection());
        return dao.getPersons(person);
    }
        
    private Connection getConnection() {
        Connection result = null;
        try {
            result = dataSource.getConnection();
        } 
        catch (SQLException ex) {
            Logger.getLogger(PersonWebService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
}
