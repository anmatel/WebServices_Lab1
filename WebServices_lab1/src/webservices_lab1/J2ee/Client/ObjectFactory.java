package webservices_lab1.J2ee.Client;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;
import webservices_lab1.Client.GetPersonsResponse;
import webservices_lab1.Client.Person;
import webservices_lab1.Client.PostgreSQLDAO;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the webservices_lab1.Client package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _GetPersonsResponse_QNAME = new QName("http://webservices_lab1/", "getPersonsResponse");
    private final static QName _GetPersons_QNAME = new QName("http://webservices_lab1/", "getPersons");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: webservices_lab1.Client
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link GetPersons }
     * 
     */
    public webservices_lab1.Client.GetPersons createGetPersons() {
        return new webservices_lab1.Client.GetPersons();
    }

    /**
     * Create an instance of {@link GetPersonsResponse }
     * 
     */
    public GetPersonsResponse createGetPersonsResponse() {
        return new GetPersonsResponse();
    }

    /**
     * Create an instance of {@link PostgreSQLDAO }
     * 
     */
    public PostgreSQLDAO createPostgreSQLDAO() {
        return new PostgreSQLDAO();
    }

    /**
     * Create an instance of {@link Person }
     * 
     */
    public Person createPerson() {
        return new Person();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetPersonsResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservices_lab1/", name = "getPersonsResponse")
    public JAXBElement<GetPersonsResponse> createGetPersonsResponse(GetPersonsResponse value) {
        return new JAXBElement<GetPersonsResponse>(_GetPersonsResponse_QNAME, GetPersonsResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetPersons }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservices_lab1/", name = "getPersons")
    public JAXBElement<webservices_lab1.Client.GetPersons> createGetPersons(webservices_lab1.Client.GetPersons value) {
        return new JAXBElement<webservices_lab1.Client.GetPersons>(_GetPersons_QNAME, webservices_lab1.Client.GetPersons.class, null, value);
    }

}
