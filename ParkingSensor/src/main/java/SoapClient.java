import javax.xml.ws.BindingProvider;
import soap.*;

public class SoapClient {
    private static void setCred(SpotService service) {
        BindingProvider provider = (BindingProvider) service;
        provider.getRequestContext().put(BindingProvider.USERNAME_PROPERTY, "hel");
        provider.getRequestContext().put(BindingProvider.PASSWORD_PROPERTY, "kol");
    }

    private static SpotService init() {
        System.out.println("Create Web Service Client...");
        SpotServiceService service1 = new SpotServiceService();
        System.out.println("Create Web Service...");
        SpotService port1 = service1.getSpotServicePort();
        System.out.println("Call Web Service Operation...");

        setCred(port1);

        return port1;
    }

    public static void setAsOccupied(int spot_id) {
        SpotService service = init();
        service.updateSpotOccupied(spot_id);

    }

    public static void setAsFree(int spot_id) {
        SpotService service = init();
        service.updateSpotFree(spot_id);
    }
}