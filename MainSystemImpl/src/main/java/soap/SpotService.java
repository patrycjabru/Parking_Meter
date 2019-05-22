package soap;

import database.ParkingSpotDAO;

import javax.ejb.Stateless;
import javax.jws.WebMethod;
import javax.jws.WebService;

@Stateless
@WebService
public class SpotService{

    @WebMethod(operationName = "updateSpotOccupied")
    public void updateSpotOccupied(int spot_id) {
        ParkingSpotDAO.setSpotAsOccupied(spot_id);
    }

    @WebMethod(operationName = "updateSpotFree")
    public void updateSpotFree(int spot_id) {
        ParkingSpotDAO.setSpotAsFree(spot_id);
    }
}
