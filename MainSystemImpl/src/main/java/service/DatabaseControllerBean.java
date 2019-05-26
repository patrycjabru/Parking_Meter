package service;

import database.EmployeeDAO;
import entities.Employee;
import service.local.DatabaseControllerLocal;
import service.remote.DatabaseControllerRemote;

import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;

@Local(DatabaseControllerLocal.class)
@Remote(DatabaseControllerRemote.class)
@Stateless
public class DatabaseControllerBean implements DatabaseControllerLocal, DatabaseControllerRemote {

    public Employee getEmployeeByName(String name) {
        return EmployeeDAO.getByName(name);
    }
}
