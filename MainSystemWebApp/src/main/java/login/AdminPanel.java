package login;

import entities.Employee;
import entities.Zone;
import service.DatabaseController;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.util.List;

@ManagedBean(name = "edit")
@SessionScoped
public class AdminPanel {

    private int pickedZoneID;
    private int pickedEmployeeID;
    private List<Zone> availableZones;
    private List<Employee> employeeList;
    private String newUserLogin;
    private String newUserPassword;

    @EJB(lookup = "java:global/MainSystemImpl-1.0/DatabaseControllerBean!service.remote.DatabaseControllerRemote")
    DatabaseController databaseController;


    public void addEmployee(){
        databaseController.addEmployee(newUserLogin, newUserPassword, pickedZoneID);
    }

    public void changeZone(){
        databaseController.changeZoneForEmployee(pickedEmployeeID, pickedZoneID);
    }


    // GETTERS & SETTERS

    public int getPickedEmployeeID() {
        return pickedEmployeeID;
    }

    public void setPickedEmployeeID(int pickedEmployeeID) {
        this.pickedEmployeeID = pickedEmployeeID;
    }

    public List<Employee> getEmployeeList() {
        employeeList = databaseController.getEmployees();
        return employeeList;
    }

    public void setEmployeeList(List<Employee> employeeList) {
        this.employeeList = employeeList;
    }

    public int getPickedZoneID() {
        return pickedZoneID;
    }

    public void setPickedZoneID(int pickedZoneID) {
        this.pickedZoneID = pickedZoneID;
    }

    public List<Zone> getAvailableZones() {
        availableZones = databaseController.getZonesWithoutEmployee();
        return availableZones;
    }

    public void setAvailableZones(List<Zone> availableZones) {
        this.availableZones = availableZones;
    }

    public String getNewUserLogin() {
        return newUserLogin;
    }

    public void setNewUserLogin(String newUserLogin) {
        this.newUserLogin = newUserLogin;
    }

    public String getNewUserPassword() {
        return newUserPassword;
    }

    public void setNewUserPassword(String newUserPassword) {
        this.newUserPassword = newUserPassword;
    }
}
