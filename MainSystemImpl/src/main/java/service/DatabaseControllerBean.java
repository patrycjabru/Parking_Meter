package service;

import database.DatabaseOperationHelper;
import database.EmployeeDAO;
import database.ParkingSpotDAO;
import database.ZoneDAO;
import entities.Employee;
import entities.UISpot;
import entities.Zone;
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

    //@EJB
    //MDBSender mdbSender;

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

    public List<Zone> getZonesWithoutEmployee(){
        return ZoneDAO.getWithoutEmployeeID();
    }

    public void addEmployee(String newUserLogin, String newUserPassword, int pickedZoneID){
        Zone pickedZone = ZoneDAO.getById(pickedZoneID);
        Employee newEmployee = new Employee();
        newEmployee.setName(newUserLogin);
        newEmployee.setPassword(Hash.hashMD5(newUserPassword));
        newEmployee.setIsAdmin(false);
        newEmployee.setZone(pickedZone);
        EmployeeDAO.add(newEmployee);
        if(pickedZone != null){
            System.out.println("pickedzone != null");
            Zone zone = ZoneDAO.getById(pickedZone.getId());
            Employee employee = EmployeeDAO.getByName(newUserLogin);
            zone.setEmployee(employee);
            ZoneDAO.update(zone);
        }
    }

    public List<Employee> getEmployees(){
        return EmployeeDAO.getAllEmployees();
    }

    public void changeZoneForEmployee(int pickedEmployeeID, int pickedZoneID){
        Employee pickedEmployee = EmployeeDAO.getById(pickedEmployeeID);
        Zone oldZone = pickedEmployee.getZone();
        Zone pickedZone = ZoneDAO.getById(pickedZoneID);
        pickedZone.setEmployee(pickedEmployee);
        ZoneDAO.update(pickedZone);
        EmployeeDAO.updateZone(pickedEmployee.getId(), pickedZone.getId());
        if (oldZone != null) {
            oldZone.setEmployee(null);
            ZoneDAO.update(oldZone);
        }
    }

    public void testSender(){
        System.out.println("Controller");
        //mdbSender.sendMessage("Teścik kolejek");
        System.out.println("/Controller");
    }
}
