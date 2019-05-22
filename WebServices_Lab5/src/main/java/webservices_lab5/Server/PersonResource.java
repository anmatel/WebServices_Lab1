package webservices_lab5.Server;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import com.sun.jersey.core.header.FormDataContentDisposition;
import com.sun.jersey.multipart.FormDataParam;
import java.util.Date;
import java.util.List;
import javax.ws.rs.*;

@Path("/persons")
@Produces({MediaType.APPLICATION_JSON})
public class PersonResource {

    @GET
    @Path("/get")
    public List<Person> getPersons(
            @QueryParam("name") String name,
            @QueryParam("name") String surname,
            @QueryParam("name") Date dateOfBirth,
            @QueryParam("name") String sex) {
        List<Person> persons = new PostgreSQLDAO().getPersons(name, surname, dateOfBirth, sex);
        return persons;
    }

    @POST
    @Path("/create")
    public Integer createPerson(
            @QueryParam("name") String name,
            @QueryParam("name") String surname,
            @QueryParam("name") Date dateOfBirth,
            @QueryParam("name") String sex) {
        int id = new PostgreSQLDAO().createPerson(name, surname, dateOfBirth, sex);
        return id;
    }

    @POST
    @Path("/update")
    public String updatePerson(
            @QueryParam("id") int id,
            @QueryParam("name") String name,
            @QueryParam("name") String surname,
            @QueryParam("name") Date dateOfBirth,
            @QueryParam("name") String sex) {
        String status = new PostgreSQLDAO().updatePerson(id, name, surname, dateOfBirth, sex);
        return status;
    }

    @DELETE
    @Path("/delete")
    public String deletePerson(@QueryParam("id") int id) {
        String status = new PostgreSQLDAO().deletePerson(id);
        return status;
    }

    @POST
    @Path("/uploadFile")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public Response uploadFile(
            @FormDataParam("file") InputStream fileInputStream,
            @FormDataParam("file") FormDataContentDisposition contentDispositionHeader) {
        String filePath = "D:\\tmp\\".concat(contentDispositionHeader.getFileName());

        try (FileOutputStream output = new FileOutputStream(new File(filePath))) {
            byte[] buffer = new byte[fileInputStream.available()];
            fileInputStream.read(buffer, 0, buffer.length);
            output.write(buffer, 0, buffer.length);
            fileInputStream.close();    
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        String message = "File saved to server location : " + filePath;
        return Response.status(200).entity(message).build();
    }
}
