package Standalone.Client;

import Standalone.Server.Person;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.GenericType;
import com.sun.jersey.api.client.WebResource;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.core.MediaType;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;

public class WebClient {

    private static final String URL = "http://localhost:1111/rest/persons";

    public static void main(String[] args) throws DatatypeConfigurationException, ParseException {
        Client client = Client.create();

        Person inputPerson = new Person();
        inputPersonsDataFromConsole(inputPerson);

        printList(getAllPersons(client, inputPerson));
    }

    private static void inputPersonsDataFromConsole(Person p) throws DatatypeConfigurationException, ParseException {
        Scanner in = new Scanner(System.in);

        System.out.print("Input fields for search (name, surname, date of birth (dd-MM-yyyy), sex) separated by spaces: ");
        String params = in.nextLine();

        for (String param : params.split(" ")) {
            System.out.print("Input " + param + ": ");
            try {
                switch (param) {
                    case ("name"):
                        p.setName(in.nextLine());
                        break;
                    case ("surname"):
                        p.setSurname(in.nextLine());
                        break;
                    case ("date of birth"):
                        SimpleDateFormat sdfo = new SimpleDateFormat("yyyy-MM-dd");
                        p.setDateOfBirth(sdfo.parse(in.nextLine()));
                        break;
                    case ("sex"):
                        p.setSex(in.nextLine());
                        break;
                }
            } catch (ParseException ex) {
                Logger.getLogger(WebClient.class.getName()).log(Level.SEVERE, "Wrong format of date", ex);
            } catch (InputMismatchException ex) {
                Logger.getLogger(WebClient.class.getName()).log(Level.SEVERE, "Wrong number input value", ex);
            }
        }
    }

    private static List<Person> getAllPersons(Client client, Person p) {
        WebResource webResource = client.resource(URL);
        
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        
        if (p != null) {
            webResource = webResource.queryParam("name", p.getName());
            webResource = webResource.queryParam("surname", p.getSurname());
            webResource = webResource.queryParam("dateOfBirth", df.format(p.getDateOfBirth()));
            webResource = webResource.queryParam("sex", p.getSex());
        }
        ClientResponse response = webResource.accept(MediaType.APPLICATION_JSON).get(ClientResponse.class);
        if (response.getStatus() != ClientResponse.Status.OK.getStatusCode()) {
            throw new IllegalStateException("Request failed");
        }
        GenericType<List<Person>> type = new GenericType<List<Person>>() {
        };
        return response.getEntity(type);
    }

    private static void printList(List<Person> persons) {
        for (Person person : persons) {
            System.out.println(person);
        }
    }
}
