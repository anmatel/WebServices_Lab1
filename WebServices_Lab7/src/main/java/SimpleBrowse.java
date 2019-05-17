
import java.util.List;
import org.apache.juddi.api_v3.AccessPointType;
import org.apache.juddi.v3.client.UDDIConstants;
import org.apache.juddi.v3.client.config.UDDIClient;
import org.apache.juddi.v3.client.transport.Transport;
import org.uddi.api_v3.AccessPoint;
import org.uddi.api_v3.FindService;
import org.uddi.api_v3.FindQualifiers;
import org.uddi.api_v3.AuthToken;
import org.uddi.api_v3.BindingTemplate;
import org.uddi.api_v3.BindingTemplates;
import org.uddi.api_v3.BusinessDetail;
import org.uddi.api_v3.BusinessInfos;
import org.uddi.api_v3.ServiceList;
import org.uddi.api_v3.BusinessService;
import org.uddi.api_v3.CategoryBag;
import org.uddi.api_v3.Contacts;
import org.uddi.api_v3.Description;
import org.uddi.api_v3.DiscardAuthToken;
import org.uddi.api_v3.GetAuthToken;
import org.uddi.api_v3.GetBusinessDetail;
import org.uddi.api_v3.GetServiceDetail;
import org.uddi.api_v3.KeyedReference;
import org.uddi.api_v3.Name;
import org.uddi.api_v3.ServiceDetail;
import org.uddi.api_v3.ServiceInfos;
import org.uddi.v3_service.UDDIInquiryPortType;
import org.uddi.v3_service.UDDISecurityPortType;

public class SimpleBrowse {

    private UDDISecurityPortType security = null;
    private UDDIInquiryPortType inquiry = null;

    private AuthToken authToken;

    public SimpleBrowse(String login, String password) {
        try {
            // create a manager and read the config in the archive; 
            // you can use your config file name
            UDDIClient client = new UDDIClient("META-INF/uddi.xml");
            // a UDDIClient can be a client to multiple UDDI nodes, so 
            // supply the nodeName (defined in your uddi.xml.
            // The transport can be WS, inVM, RMI etc which is defined in the uddi.xml
            Transport transport = client.getTransport("default");
            // Now you create a reference to the UDDI API
            security = transport.getUDDISecurityService();
            inquiry = transport.getUDDIInquiryService();

            GetAuthToken getAuthTokenMyPub = new GetAuthToken();
            getAuthTokenMyPub.setUserID(login);
            getAuthTokenMyPub.setCred(password);
            authToken = security.getAuthToken(getAuthTokenMyPub);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void findAndCallService(String serviceName, String serviceKey){
        try {
            FindService fs = new FindService();
            fs.setAuthInfo(authToken.getAuthInfo());

            FindQualifiers fq = new FindQualifiers();
            fq.getFindQualifier().add(UDDIConstants.APPROXIMATE_MATCH);

            fs.setFindQualifiers(fq);
            Name searchname = new Name();
            searchname.setValue("%" + serviceName);
            fs.getName().add(searchname);
            ServiceList serviceList = inquiry.findService(fs);

            GetServiceDetail gsd = new GetServiceDetail();
            for (int i = 0; i < serviceList.getServiceInfos().getServiceInfo().size(); i++) {
                gsd.getServiceKey().add(serviceList.getServiceInfos().getServiceInfo().get(i).getServiceKey());
            }
            ServiceDetail sd = inquiry.getServiceDetail(gsd);
            PrintInfo(sd.getBusinessService());
            
            gsd.setAuthInfo(authToken.getAuthInfo());
            gsd.getServiceKey().add(serviceKey);
            sd = inquiry.getServiceDetail(gsd);
            
            if (sd == null || sd.getBusinessService() == null || sd.getBusinessService().isEmpty()) {
                System.out.println("Can not find service with key " + serviceKey);
                return;
            }

            List<BusinessService> services = sd.getBusinessService();
            BusinessService businessService = services.get(0);
            BindingTemplates bindingTemplates = businessService.getBindingTemplates();
            if (bindingTemplates == null || bindingTemplates.getBindingTemplate().isEmpty()) {
                System.out.println("No binding template for service with key " + serviceKey);
                return;
            }
            for (BindingTemplate bindingTemplate : bindingTemplates.getBindingTemplate()) {
                AccessPoint accessPoint = bindingTemplate.getAccessPoint();
                System.out.println(accessPoint.getValue());
            }

            security.discardAuthToken(new DiscardAuthToken(authToken.getAuthInfo()));

        } catch (Exception e) {
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
