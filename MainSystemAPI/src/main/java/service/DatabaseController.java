package service;

import entities.Employee;
import entities.UISpot;
import entities.Zone;

import java.util.List;

public interface DatabaseController {

    Employee getEmployeeByName(String name);
    List<UISpot> getParkingStatus(Employee employee);
    boolean changePassword(int employeeId, String oldPassword, String newPassword);
    List<Zone> getZonesWithoutEmployee();
    void addEmployee(String newUserLogin, String newUserPassword, int pickedZoneID);
    List<Employee> getEmployees();
    void changeZoneForEmployee(int pickedEmployeeID, int pickedZoneID);
    void testSender();
}
