package service;

import entities.Employee;

public interface DatabaseController {
    Employee getEmployeeByName(String name);
}
