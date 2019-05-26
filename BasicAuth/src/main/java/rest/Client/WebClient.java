package rest.Client;

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
import javax.ws.rs.core.Response.StatusType;
import javax.xml.datatype.DatatypeConfigurationException;
import rest.server.Person;

public class WebClient {

    private static final String URL = "http://localhost:1111/rest/persons";
    public final static String LOGIN = "sa";
    public final static String PASSWORD = "12345";

    public static void main(String[] args) throws DatatypeConfigurationException, ParseException {
        Client client = Client.create();
        Person inputPerson = new Person();
        int id;

        Scanner in = new Scanner(System.in);
        while (true) {
            System.out.println("Select the type of action: 1 - select, 2 - insert, 3 - update, 4 - delete, 5 - quit");
            String actionType = in.nextLine();
            try {
                switch (actionType) {
                    case "1":
                        inputPersonsDataFromConsole(in, inputPerson);
                        printList(getAllPersons(client, inputPerson));
                        break;
                    case "2":
                        inputPersonsDataFromConsole(in, inputPerson);
                        id = createPerson(client, inputPerson);
                        System.out.println("Person was created with id " + id);
                        break;
                    case "3":
                        System.out.println("Input person id:");
                        id = in.nextInt();
                        inputPersonsDataFromConsole(in, inputPerson);
                        updatePerson(client, id, inputPerson);
                        break;
                    case "4":
                        System.out.println("Input person id:");
                        id = in.nextInt();
                        deletePerson(client, id);
                        break;
                    case "5":
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Wrong choose");
                        break;
                }
            } catch (Exception ex) {
                System.out.println("Received error: " + ex.getMessage());
            }
        }
    }

    private static void inputPersonsDataFromConsole(Scanner in, Person p) throws DatatypeConfigurationException {
        String value;
        try {
            System.out.print("input name: ");
            value = in.nextLine();
            if (!value.isEmpty()) {
                p.setName(value);
            }
            System.out.print("input suname: ");
            value = in.nextLine();
            if (!value.isEmpty()) {
                p.setSurname(value);
            }
            System.out.print("input date of birth (yyyy-MM-dd): ");
            value = in.nextLine();
            if (!value.isEmpty()) {
                SimpleDateFormat sdfo = new SimpleDateFormat("yyyy-MM-dd");
                p.setDateOfBirth(sdfo.parse(value));
            }
            System.out.print("input sex (male/female): ");
            value = in.nextLine();
            if (!value.isEmpty()) {
                p.setSex(value);
            }
            System.out.print("Your query is "+p.toString());
        } catch (ParseException ex) {
            Logger.getLogger(WebClient.class.getName()).log(Level.SEVERE, "Wrong format of date", ex);
        } catch (InputMismatchException ex) {
            Logger.getLogger(WebClient.class.getName()).log(Level.SEVERE, "Wrong number input value", ex);
        }
    }

    private static List<Person> getAllPersons(Client client, Person p) {
        WebResource webResource = client.resource(URL.concat("/get"));

        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");

        if (p != null) {
            webResource = webResource.queryParam("name", p.getName());
            webResource = webResource.queryParam("surname", p.getSurname());
            webResource = webResource.queryParam("dateOfBirth", df.format(p.getDateOfBirth()));
            webResource = webResource.queryParam("sex", p.getSex());

        }
        ClientResponse response = webResource.accept(MediaType.APPLICATION_JSON).get(ClientResponse.class
        );
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

    private static int createPerson(Client client, Person p) {
        WebResource webResource = client.resource(URL.concat("/create"));

        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");

        if (p != null) {
            webResource = webResource.queryParam("name", p.getName());
            webResource = webResource.queryParam("surname", p.getSurname());
            webResource = webResource.queryParam("dateOfBirth", df.format(p.getDateOfBirth()));
            webResource = webResource.queryParam("sex", p.getSex());

        }

        ClientResponse response = webResource.accept(MediaType.APPLICATION_JSON).post(ClientResponse.class
        );
        if (response.getStatus() != ClientResponse.Status.OK.getStatusCode()) {
            throw new IllegalStateException("Request failed");
        }
        GenericType<Integer> type = new GenericType<Integer>() {
        };
        return response.getEntity(type);
    }

    private static StatusType updatePerson(Client client, Integer id, Person p) {
        WebResource webResource = client.resource(URL.concat("/update"));

        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");

        if (p != null) {
            webResource = webResource.queryParam("name", Integer.toString(id));
            webResource = webResource.queryParam("name", p.getName());
            webResource = webResource.queryParam("surname", p.getSurname());
            webResource = webResource.queryParam("dateOfBirth", df.format(p.getDateOfBirth()));
            webResource = webResource.queryParam("sex", p.getSex());

        }

        ClientResponse response = webResource.accept(MediaType.APPLICATION_JSON).post(ClientResponse.class
        );
        if (response.getStatus() != ClientResponse.Status.OK.getStatusCode()) {
            throw new IllegalStateException("Request failed");
        }

        return response.getStatusInfo();
    }

    private static StatusType deletePerson(Client client, Integer id) {
        WebResource webResource = client.resource(URL.concat("/delete"));

        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");

        if (id != null) {
            webResource = webResource.queryParam("name", Integer.toString(id));

        }

        ClientResponse response = webResource.accept(MediaType.APPLICATION_JSON).delete(ClientResponse.class
        );
        if (response.getStatus() != ClientResponse.Status.OK.getStatusCode()) {
            throw new IllegalStateException("Request failed");
        }

        return response.getStatusInfo();
    }
}
