package login;

import entities.Employee;
import service.DatabaseController;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import java.security.Principal;

@ManagedBean
@SessionScoped
public class Auth {

    private Employee employee; // The JPA entity.

    @EJB(lookup = "java:global/MainSystemImpl-1.0/DatabaseControllerBean!service.remote.DatabaseControllerRemote")
    DatabaseController databaseController;

    @PostConstruct
    public void init(){
        if (employee == null) {
            Principal principal = FacesContext.getCurrentInstance().getExternalContext().getUserPrincipal();
            if (principal != null) {
                employee = databaseController.getEmployeeByName(principal.getName());
            }
        }
    }

    public Employee getEmployee() {
        return employee;
    }

    public String getEmployeeName(){
        return employee.getName();
    }
}