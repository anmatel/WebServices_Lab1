package webservices_lab1.J2ee.Client;

import java.net.MalformedURLException;
import java.net.URL;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Scanner;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import webservices_lab1.Client.Person;
import webservices_lab1.Client.PersonService;
import webservices_lab1.Client.PostgreSQLDAO;

public class App {
    public static void main(String[] args) throws MalformedURLException, ParseException, DatatypeConfigurationException {
        URL url = new URL("http://localhost:1111/PersonService?wsdl");
        PersonService personService = new PersonService(url);
        
        Person inputData = inputPersonData();
        

        List<Person> persons = personService.getPersonWebServicePort().getPersons(inputData, new PostgreSQLDAO());
        for (Person person : persons) {
            System.out.println("name: " + person.getName() + 
                    ", surname: " + person.getSurname() + 
                    ", date of birth: " + person.getDateOfBirth() +
                    ", sex: " + person.getSex());
    }
        System.out.println("Total persons: " + persons.size());
    }
    
    private static Person inputPersonData() throws ParseException, DatatypeConfigurationException
    {
        Person person = new Person();
        Scanner in = new Scanner(System.in);
        
        System.out.println("Input data to search person...");
        
        System.out.println("Name: ");
        person.name = in.nextLine();
        
        System.out.println("Surname: ");
        person.surname = in.nextLine();
        
        System.out.println("Date of birth (in format '31/12/1999': ");
        DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        Date date = formatter.parse(in.nextLine());
        GregorianCalendar cal = new GregorianCalendar();
        cal.setTime(date);
        person.dateOfBirth = DatatypeFactory.newInstance().newXMLGregorianCalendar(cal);;
        
        System.out.println("Sex: ");
        person.sex = in.nextLine();
        
        return person;
    }
}
