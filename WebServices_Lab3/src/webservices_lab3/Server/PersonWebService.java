package webservices_lab3.Server;

import java.util.List;
import javax.jws.WebMethod;
import javax.jws.WebService; 
import webservices_lab3.Errors.IllegalEnteredDataException;
 
@WebService(serviceName = "PersonService") 
public class PersonWebService { 
 
    @WebMethod(operationName = "getPersons")     
    public List<Person> getPersons(Person p, PostgreSQLDAO dao) throws IllegalEnteredDataException{         
        List<Person> persons = dao.getPersons(p);
        return persons;
    }
    
    @WebMethod(operationName = "createPerson")
    public int createPerson(Person p, PostgreSQLDAO dao) throws IllegalEnteredDataException {
        int personId = dao.createPerson(p);
        return personId;
    }
    
    @WebMethod(operationName = "updatePerson") 
    public String updatePerson(Integer id, Person p, PostgreSQLDAO dao) throws IllegalEnteredDataException{
        String response = dao.updatePerson(id, p);
        return response;
    }
    
    @WebMethod(operationName = "deletePerson")
    public String deletePerson(Integer id, PostgreSQLDAO dao) throws IllegalEnteredDataException{
        String response = dao.deletePerson(id);
        return response;
    }
}
