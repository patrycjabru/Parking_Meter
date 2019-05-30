package service;

import entities.Employee;
import entities.UISpot;

import java.util.List;

public interface DatabaseController {

    Employee getEmployeeByName(String name);
    List<UISpot> getParkingStatus(Employee employee);
    void changePassword(int employeeId, String oldPassword, String newPassword);
    void testSender();
}
