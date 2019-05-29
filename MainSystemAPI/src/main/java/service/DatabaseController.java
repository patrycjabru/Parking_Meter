package service;

import entities.Employee;
import entities.UISpot;

import java.util.List;

public interface DatabaseController {
    Employee getEmployeeByName(String name);

    List<UISpot> getParkingStatus(Employee employee);
}
