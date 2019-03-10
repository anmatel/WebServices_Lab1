package webservices_lab1.Server;

import javax.xml.ws.Endpoint;

public class App {
     public static void main(String[] args) {         
         String url = "http://0.0.0.0:1111/PersonService";
         Endpoint.publish(url, new PersonWebService()); 
     }
}