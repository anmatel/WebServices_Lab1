package soap.Server;

import java.util.List;
import javax.jws.WebMethod;
import javax.jws.WebService; 
import soap.Errors.*;
import java.util.Map;
import soap.Client.WebClient;
import javax.xml.ws.handler.MessageContext;
import javax.xml.ws.WebServiceContext;
import javax.annotation.Resource;
 
@WebService(serviceName = "PersonService") 
public class PersonWebService { 
    @Resource WebServiceContext wsctx;
 
    @WebMethod(operationName = "getPersons")     
    public List<Person> getPersons(Person p, PostgreSQLDAO dao) throws IllegalEnteredDataException, AuthentificationException{         
        doAuthentification();
        List<Person> persons = dao.getPersons(p);
        return persons;
    }
    
    @WebMethod(operationName = "createPerson")
    public int createPerson(Person p, PostgreSQLDAO dao) throws IllegalEnteredDataException, AuthentificationException {
        doAuthentification();
        int personId = dao.createPerson(p);
        return personId;
    }
    
    @WebMethod(operationName = "updatePerson") 
    public String updatePerson(Integer id, Person p, PostgreSQLDAO dao) throws IllegalEnteredDataException, AuthentificationException{
        doAuthentification();
        String response = dao.updatePerson(id, p);
        return response;
    }
    
    @WebMethod(operationName = "deletePerson")
    public String deletePerson(Integer id, PostgreSQLDAO dao) throws IllegalEnteredDataException, AuthentificationException{
        doAuthentification();
        String response = dao.deletePerson(id);
        return response;
    }
    
    private void doAuthentification() throws AuthentificationException{
        MessageContext mctx = wsctx.getMessageContext();
        Map http_headers = (Map) mctx.get(MessageContext.HTTP_REQUEST_HEADERS);

        List userList = (List) http_headers.get("Username");
        List passList = (List) http_headers.get("Password");
        
        if (userList == null || userList.isEmpty()) {
            AuthentificationFault authFault = AuthentificationFault.defaultInstance("Empty username");
            throw new AuthentificationException(authFault.getMessage(), authFault);
        }
        
        if (passList == null || passList.isEmpty()) {
            AuthentificationFault authFault = AuthentificationFault.defaultInstance("Empty password");
            throw new AuthentificationException(authFault.getMessage(), authFault);
        }
        
        if (!(userList.get(0).equals(WebClient.LOGIN) & passList.get(0).equals(WebClient.PASSWORD))){
            AuthentificationFault authFault = AuthentificationFault.defaultInstance("Wrong authentification");
            throw new AuthentificationException(authFault.getMessage(), authFault);
        }
    }
}
