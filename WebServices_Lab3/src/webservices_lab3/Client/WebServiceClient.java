package webservices_lab3.Client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;

public class WebServiceClient {

    private static PersonService personService;

    public static void main(String[] args) throws MalformedURLException, ParseException, DatatypeConfigurationException, IOException {
        URL url = new URL("http://localhost:1111/PersonService?wsdl");
        PersonService personService = new PersonService(url);

        Scanner in = new Scanner(System.in);
        while (true) {
            System.out.println("Select the type of action: 1 - select, 2 - insert, 3 - update, 4 - delete, 5 - quit");
            String actionType = in.nextLine();
            try {
                switch (actionType) {
                    case "1":
                        getPerson(in);
                        break;
                    case "2":
                        createPerson(in);
                        break;
                    case "3":
                        updatePerson(in);
                        break;
                    case "4":
                        deletePerson(in);
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

    private static void getPerson(Scanner in) throws ParseException, DatatypeConfigurationException {
        Person inputPerson = inputPersonsData(in);
        try {
            List<Person> persons = personService.getPersonWebServicePort().getPersons(inputPerson, new PostgreSQLDAO());
            for (Person person : persons) {
                System.out.println("name: " + person.getName()
                        + ", surname: " + person.getSurname()
                        + ", date of birth: " + person.getDateOfBirth()
                        + ", sex: " + person.getSex());
            }
            System.out.println("Total persons: " + persons.size());
        } catch (IllegalEnteredDataException ex) {
            ex.getMessage();
        }
    }

    private static void createPerson(Scanner in) throws ParseException, DatatypeConfigurationException {
        Person inputPerson = inputPersonsData(in);
        try {
            Integer id = personService.getPersonWebServicePort().createPerson(inputPerson, new PostgreSQLDAO());
            System.out.println("Person was created with id: " + id);
        } catch (IllegalEnteredDataException ex) {
            ex.getMessage();
        }
    }

    private static void updatePerson(Scanner in) throws ParseException, DatatypeConfigurationException {
        System.out.println("Input person's id...");
        Integer id = in.nextInt();

        Person inputPerson = inputPersonsData(in);
        try {
            String response = personService.getPersonWebServicePort().updatePerson(id, inputPerson, new PostgreSQLDAO());
            System.out.println(response);
        } catch (IllegalEnteredDataException ex) {
            ex.getMessage();
        }
    }

    private static void deletePerson(Scanner in) throws ParseException {
        System.out.println("Input person's id...");
        Integer id = in.nextInt();
        try {
            String response = personService.getPersonWebServicePort().deletePerson(id, new PostgreSQLDAO());
            System.out.println(response);
        } catch (IllegalEnteredDataException ex) {
            ex.getMessage();
        }

    }

    private static Person inputPersonsData(Scanner in) throws ParseException, DatatypeConfigurationException {
        Person inputPerson = new Person();

        System.out.println("Input person's data...");

        System.out.println("Name: ");
        inputPerson.name = in.nextLine();

        System.out.println("Surname: ");
        inputPerson.surname = in.nextLine();

        System.out.println("Date of birth (in format '31/12/1999': ");
        DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        Date date = formatter.parse(in.nextLine());
        GregorianCalendar cal = new GregorianCalendar();
        cal.setTime(date);
        inputPerson.dateOfBirth = DatatypeFactory.newInstance().newXMLGregorianCalendar(cal);;

        System.out.println("Sex: ");
        inputPerson.sex = in.nextLine();
        return inputPerson;
    }
}
