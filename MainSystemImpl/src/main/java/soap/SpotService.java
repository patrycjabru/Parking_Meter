package soap;

import database.ParkingSpotDAO;
import service.AlertManager;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.jws.WebMethod;
import javax.jws.WebService;
import java.text.SimpleDateFormat;
import java.util.Date;

@Stateless
@WebService
public class SpotService{

    @EJB(lookup = "java:global/MainSystemImpl-1.0/AlertManagerBean!service.local.AlertManagerLocal")
    AlertManager alertManager;

    @WebMethod(operationName = "updateSpotOccupied")
    public void updateSpotOccupied(int spot_id) {
        ParkingSpotDAO.setSpotAsOccupied(spot_id);
        alertManager.scheduleSpotCheck(spot_id);
    }

    @WebMethod(operationName = "updateSpotFree")
    public void updateSpotFree(int spot_id) {
        ParkingSpotDAO.setSpotAsFree(spot_id);
        Date modifyDate = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
        String dateString = formatter.format(modifyDate);
        alertManager.send("date:"+dateString);
    }
}
