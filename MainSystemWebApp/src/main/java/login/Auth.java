package login;

import entities.Employee;
import entities.UISpot;
import service.DatabaseController;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import java.security.Principal;
import java.util.List;

@ManagedBean
@SessionScoped
public class Auth {

    private Employee employee; // The JPA entity.

    private List<UISpot> parkingStatus;

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

    public void testSender(){
        System.out.println("Click");
        databaseController.testSender();
        System.out.println("/Click");
    }

    public Employee getEmployee() {
        return employee;
    }

    public List<UISpot> getParkingStatus() {
        parkingStatus = databaseController.getParkingStatus(employee);
        return parkingStatus;
    }

    public String getEmployeeName(){
        return employee.getName();
    }
}