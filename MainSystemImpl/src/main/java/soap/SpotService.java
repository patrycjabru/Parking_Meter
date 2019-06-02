package soap;

import database.ParkingSpotDAO;
import service.AlertManager;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.jws.WebMethod;
import javax.jws.WebService;

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
    }
}
