import javax.xml.ws.BindingProvider;
import soap.*;

public class SoapClient {
    private static SpotService init() {
        SpotServiceService service = new SpotServiceService();
        SpotService port = service.getSpotServicePort();
        return port;
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