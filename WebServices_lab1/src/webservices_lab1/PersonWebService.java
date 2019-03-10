package webservices_lab1;

import java.util.List;
import javax.jws.WebMethod;
import javax.jws.WebService; 
 
@WebService(serviceName = "PersonService") 
public class PersonWebService { 
 
    @WebMethod(operationName = "getPersons")     
    public List<Person> getPersons(Person p, PostgreSQLDAO dao) {         
        List<Person> persons = dao.getPersons(p);
        return persons;
    }
}
