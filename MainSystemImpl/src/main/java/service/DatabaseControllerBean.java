package service;

import database.EmployeeDAO;
import database.ParkingSpotDAO;
import entities.Employee;
import entities.UISpot;
import msg.MDBSender;
import service.local.DatabaseControllerLocal;
import service.remote.DatabaseControllerRemote;
import utils.Hash;
import utils.ParkingSpotsToUIConverter;

import javax.ejb.*;
import java.util.List;

@Local(DatabaseControllerLocal.class)
@Remote(DatabaseControllerRemote.class)
@Singleton
public class DatabaseControllerBean implements DatabaseControllerLocal, DatabaseControllerRemote {

    @EJB
    MDBSender mdbSender;

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

    public void changePassword(int employeeId, String oldPassword, String newPassword){
        String hashedOldPassword = Hash.hashMD5(oldPassword);
        String hashedNewPassword = Hash.hashMD5(newPassword);
        EmployeeDAO.updatePassword(employeeId, hashedOldPassword, hashedNewPassword);
    }

    public void testSender(){
        System.out.println("Controller");
        mdbSender.sendMessage("Teścik kolejek");
        System.out.println("/Controller");
    }
}
