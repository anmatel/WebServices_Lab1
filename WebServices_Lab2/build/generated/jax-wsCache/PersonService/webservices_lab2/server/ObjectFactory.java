
package webservices_lab2.server;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the webservices_lab2.server package. 
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

    private final static QName _SendBinaryData_QNAME = new QName("http://Server.webservices_lab2/", "sendBinaryData");
    private final static QName _UpdatePerson_QNAME = new QName("http://Server.webservices_lab2/", "updatePerson");
    private final static QName _CreatePerson_QNAME = new QName("http://Server.webservices_lab2/", "createPerson");
    private final static QName _SendBinaryDataResponse_QNAME = new QName("http://Server.webservices_lab2/", "sendBinaryDataResponse");
    private final static QName _GetPersons_QNAME = new QName("http://Server.webservices_lab2/", "getPersons");
    private final static QName _GetPersonsResponse_QNAME = new QName("http://Server.webservices_lab2/", "getPersonsResponse");
    private final static QName _DeletePerson_QNAME = new QName("http://Server.webservices_lab2/", "deletePerson");
    private final static QName _CreatePersonResponse_QNAME = new QName("http://Server.webservices_lab2/", "createPersonResponse");
    private final static QName _UpdatePersonResponse_QNAME = new QName("http://Server.webservices_lab2/", "updatePersonResponse");
    private final static QName _DeletePersonResponse_QNAME = new QName("http://Server.webservices_lab2/", "deletePersonResponse");
    private final static QName _SendBinaryDataArg0_QNAME = new QName("", "arg0");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: webservices_lab2.server
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link DeletePerson }
     * 
     */
    public DeletePerson createDeletePerson() {
        return new DeletePerson();
    }

    /**
     * Create an instance of {@link CreatePersonResponse }
     * 
     */
    public CreatePersonResponse createCreatePersonResponse() {
        return new CreatePersonResponse();
    }

    /**
     * Create an instance of {@link UpdatePersonResponse }
     * 
     */
    public UpdatePersonResponse createUpdatePersonResponse() {
        return new UpdatePersonResponse();
    }

    /**
     * Create an instance of {@link DeletePersonResponse }
     * 
     */
    public DeletePersonResponse createDeletePersonResponse() {
        return new DeletePersonResponse();
    }

    /**
     * Create an instance of {@link GetPersons }
     * 
     */
    public GetPersons createGetPersons() {
        return new GetPersons();
    }

    /**
     * Create an instance of {@link GetPersonsResponse }
     * 
     */
    public GetPersonsResponse createGetPersonsResponse() {
        return new GetPersonsResponse();
    }

    /**
     * Create an instance of {@link CreatePerson }
     * 
     */
    public CreatePerson createCreatePerson() {
        return new CreatePerson();
    }

    /**
     * Create an instance of {@link SendBinaryDataResponse }
     * 
     */
    public SendBinaryDataResponse createSendBinaryDataResponse() {
        return new SendBinaryDataResponse();
    }

    /**
     * Create an instance of {@link SendBinaryData }
     * 
     */
    public SendBinaryData createSendBinaryData() {
        return new SendBinaryData();
    }

    /**
     * Create an instance of {@link UpdatePerson }
     * 
     */
    public UpdatePerson createUpdatePerson() {
        return new UpdatePerson();
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
     * Create an instance of {@link JAXBElement }{@code <}{@link SendBinaryData }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://Server.webservices_lab2/", name = "sendBinaryData")
    public JAXBElement<SendBinaryData> createSendBinaryData(SendBinaryData value) {
        return new JAXBElement<SendBinaryData>(_SendBinaryData_QNAME, SendBinaryData.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UpdatePerson }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://Server.webservices_lab2/", name = "updatePerson")
    public JAXBElement<UpdatePerson> createUpdatePerson(UpdatePerson value) {
        return new JAXBElement<UpdatePerson>(_UpdatePerson_QNAME, UpdatePerson.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CreatePerson }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://Server.webservices_lab2/", name = "createPerson")
    public JAXBElement<CreatePerson> createCreatePerson(CreatePerson value) {
        return new JAXBElement<CreatePerson>(_CreatePerson_QNAME, CreatePerson.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SendBinaryDataResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://Server.webservices_lab2/", name = "sendBinaryDataResponse")
    public JAXBElement<SendBinaryDataResponse> createSendBinaryDataResponse(SendBinaryDataResponse value) {
        return new JAXBElement<SendBinaryDataResponse>(_SendBinaryDataResponse_QNAME, SendBinaryDataResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetPersons }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://Server.webservices_lab2/", name = "getPersons")
    public JAXBElement<GetPersons> createGetPersons(GetPersons value) {
        return new JAXBElement<GetPersons>(_GetPersons_QNAME, GetPersons.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetPersonsResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://Server.webservices_lab2/", name = "getPersonsResponse")
    public JAXBElement<GetPersonsResponse> createGetPersonsResponse(GetPersonsResponse value) {
        return new JAXBElement<GetPersonsResponse>(_GetPersonsResponse_QNAME, GetPersonsResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DeletePerson }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://Server.webservices_lab2/", name = "deletePerson")
    public JAXBElement<DeletePerson> createDeletePerson(DeletePerson value) {
        return new JAXBElement<DeletePerson>(_DeletePerson_QNAME, DeletePerson.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CreatePersonResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://Server.webservices_lab2/", name = "createPersonResponse")
    public JAXBElement<CreatePersonResponse> createCreatePersonResponse(CreatePersonResponse value) {
        return new JAXBElement<CreatePersonResponse>(_CreatePersonResponse_QNAME, CreatePersonResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UpdatePersonResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://Server.webservices_lab2/", name = "updatePersonResponse")
    public JAXBElement<UpdatePersonResponse> createUpdatePersonResponse(UpdatePersonResponse value) {
        return new JAXBElement<UpdatePersonResponse>(_UpdatePersonResponse_QNAME, UpdatePersonResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DeletePersonResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://Server.webservices_lab2/", name = "deletePersonResponse")
    public JAXBElement<DeletePersonResponse> createDeletePersonResponse(DeletePersonResponse value) {
        return new JAXBElement<DeletePersonResponse>(_DeletePersonResponse_QNAME, DeletePersonResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link byte[]}{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "arg0", scope = SendBinaryData.class)
    public JAXBElement<byte[]> createSendBinaryDataArg0(byte[] value) {
        return new JAXBElement<byte[]>(_SendBinaryDataArg0_QNAME, byte[].class, SendBinaryData.class, ((byte[]) value));
    }

}
