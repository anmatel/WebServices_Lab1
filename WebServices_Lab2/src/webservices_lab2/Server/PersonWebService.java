package webservices_lab2.Server;

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
    
    @WebMethod(operationName = "createPerson")
    public int createPerson(Person p, PostgreSQLDAO dao){
        int personId = dao.createPerson(p);
        return personId;
    }
    
    @WebMethod(operationName = "updatePerson")
    public String updatePerson(Integer id, Person p, PostgreSQLDAO dao){
        String response = dao.updatePerson(id, p);
        return response;
    }
    
    @WebMethod(operationName = "deletePerson")
    public String deletePerson(Integer id, PostgreSQLDAO dao){
        String response = dao.deletePerson(id);
        return response;
    }
}