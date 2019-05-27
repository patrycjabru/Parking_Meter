package service;

import database.EmployeeDAO;
import database.ParkingSpotDAO;
import entities.Employee;
import entities.UISpot;
import service.local.DatabaseControllerLocal;
import service.remote.DatabaseControllerRemote;
import utils.ParkingSpotsToUIConverter;

import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import java.util.List;

@Local(DatabaseControllerLocal.class)
@Remote(DatabaseControllerRemote.class)
@Stateless
public class DatabaseControllerBean implements DatabaseControllerLocal, DatabaseControllerRemote {

    public Employee getEmployeeByName(String name) {
        return EmployeeDAO.getByName(name);
    }

    public List<UISpot> getParkingStatus(Employee employee){
        if(employee.getIsAdmin()){
            return ParkingSpotsToUIConverter.convertSpotsToUI(ParkingSpotDAO.getAllSpots());
        }else if (employee.getZone() == null) {
            return null;
        }else {
            return ParkingSpotsToUIConverter.convertSpotsToUI(ParkingSpotDAO.getSpotsByEmployeeId(employee.getId()));
        }
    }
}
