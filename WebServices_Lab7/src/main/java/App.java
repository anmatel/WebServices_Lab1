
import java.util.Scanner;

public class App {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String login = "uddiadmin";
        String password = "da_password1";
        String businessKey, serviceName, wsdl, serviceKey;
        
        while (true) {
            System.out.println("Choose action: 0 - register service, 1 - find service, 2 - exit");
            String action = in.nextLine();
            switch (action) {
                case "0":
                    System.out.println("Enter business key: ");
                    businessKey = in.next().trim();
                    System.out.println("Enter service name: ");
                    serviceName = in.next().trim();
                    System.out.println("Enter wsdl: ");
                    wsdl = in.next().trim();                   
                    SimplePublish sp = new SimplePublish(login, password);
                    sp.publishService(businessKey, serviceName, wsdl);
                    break;
                case "1":
                    System.out.println("Enter service name: ");
                    serviceName = in.next();
                    System.out.println("Enter service key: ");
                    serviceKey = in.next();
                    SimpleBrowse sb = new SimpleBrowse(login, password);
                    sb.findAndCallService(serviceName, serviceKey);
                    break;
                case "2":
                    System.exit(0);
                    break;
                default:
                    System.out.println("Wrong input. Choose number 0, 1 or 2");
                    break;
            }
        }
    }
}
