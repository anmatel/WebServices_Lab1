
import java.util.List;
import org.uddi.api_v3.*;
import org.apache.juddi.api_v3.*;
import org.apache.juddi.v3.client.config.UDDIClient;
import org.apache.juddi.v3.client.transport.Transport;
import org.uddi.v3_service.UDDISecurityPortType;
import org.uddi.v3_service.UDDIPublicationPortType;

public class SimplePublish {

    private UDDISecurityPortType security = null;
    private UDDIPublicationPortType publish = null;
    
    private AuthToken authToken;

    public SimplePublish(String login, String password) {
        try {
            // create a client and read the config in the archive; 
            // you can use your config file name
            UDDIClient uddiClient = new UDDIClient("META-INF/uddi.xml");
            // a UddiClient can be a client to multiple UDDI nodes, so 
            // supply the nodeName (defined in your uddi.xml.
            // The transport can be WS, inVM, RMI etc which is defined in the uddi.xml
            Transport transport = uddiClient.getTransport("default");
            // Now you create a reference to the UDDI API
            security = transport.getUDDISecurityService();
            publish = transport.getUDDIPublishService();
            
            GetAuthToken getAuthTokenMyPub = new GetAuthToken();
            getAuthTokenMyPub.setUserID(login);
            getAuthTokenMyPub.setCred(password);
            authToken = security.getAuthToken(getAuthTokenMyPub);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * This function shows you how to publish to UDDI using a fairly generic
     * mechanism that should be portable (meaning use any UDDI v3 library with
     * this code)
     */
    public void publishService(String businessKey, String serviceName, String wsdl) {
        try {
            // Creating the parent business entity that will contain our service.
            BusinessEntity myBusEntity = new BusinessEntity();
            Name myBusName = new Name();
            myBusName.setValue(businessKey);
            myBusEntity.getName().add(myBusName);

            // Adding the business entity to the "save" structure, using our publisher's authentication info and saving away.
            SaveBusiness sb = new SaveBusiness();
            sb.getBusinessEntity().add(myBusEntity);
            sb.setAuthInfo(authToken.getAuthInfo());
            BusinessDetail bd = publish.saveBusiness(sb);
            String myBusKey = bd.getBusinessEntity().get(0).getBusinessKey();
            System.out.println("myBusiness key:  " + myBusKey);

            // Creating a service to save.  Only adding the minimum data: the parent business key retrieved from saving the business 
            // above and a single name.
            BusinessService myService = new BusinessService();
            myService.setBusinessKey(myBusKey);
            Name myServName = new Name();
            myServName.setValue(serviceName);
            myService.getName().add(myServName);

            // Add binding templates, etc...
            BindingTemplate myBindingTemplate = new BindingTemplate();
            AccessPoint accessPoint = new AccessPoint();
            accessPoint.setUseType(AccessPointType.WSDL_DEPLOYMENT.toString());
            accessPoint.setValue(wsdl);
            myBindingTemplate.setAccessPoint(accessPoint);
            BindingTemplates myBindingTemplates = new BindingTemplates();
            //optional but recommended step, this annotations our binding with all the standard SOAP tModel instance infos
            myBindingTemplate = UDDIClient.addSOAPtModels(myBindingTemplate);
            myBindingTemplates.getBindingTemplate().add(myBindingTemplate);

            myService.setBindingTemplates(myBindingTemplates);

            // Adding the service to the "save" structure, using our publisher's authentication info and saving away.
            SaveService ss = new SaveService();
            ss.getBusinessService().add(myService);
            ss.setAuthInfo(authToken.getAuthInfo());
            ServiceDetail sd = publish.saveService(ss);

            security.discardAuthToken(new DiscardAuthToken(authToken.getAuthInfo()));
            // Now you have published a business and service via 
            // the jUDDI API!
            System.out.println("Success!");
            
            PrintInfo(sd.getBusinessService());
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private void PrintInfo(List<BusinessService> serviceInfos) {
        if (serviceInfos == null) {
            System.out.println("No data returned");
        } else {
            for (int i = 0; i < serviceInfos.size(); i++) {
                System.out.println("-------------------------------------------");
                System.out.println("Service Key: " + serviceInfos.get(i).getServiceKey());
                System.out.println("Owning Business Key: " + serviceInfos.get(i).getBusinessKey());
                System.out.println("Name: " + ListToString(serviceInfos.get(i).getName()));
            }
        }
    }

    private String ListToString(List<Name> name) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < name.size(); i++) {
            sb.append(name.get(i).getValue()).append(" ");
        }
        return sb.toString();
    }
}

