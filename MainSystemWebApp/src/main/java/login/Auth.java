package login;

import entities.Employee;
import entities.UISpot;
import service.DatabaseController;
import service.MessageStorage;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import java.security.Principal;
import java.util.Date;
import java.util.List;

@ManagedBean
@SessionScoped
public class Auth {

    private Employee employee; // The JPA entity.
    private String oldPswd;
    private String newPswd;
    private List<UISpot> parkingStatus;
    private Date lastModify;

    @EJB(lookup = "java:global/AlertApp-1.0/MessageStorageBean!service.remote.MessageStorageRemote")
    MessageStorage messageStorageBean;

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
        updateParkingStatus();
        lastModify = new Date();
    }

    public void updateDashboard(){
        Date msgLast = messageStorageBean.getLastModifiedDate();
        if(!msgLast.equals(lastModify)){
            System.out.println("Diff:"+ msgLast + ":"+lastModify);
            lastModify = msgLast;
            updateParkingStatus();
        }
    }

    public void changePassword(){
        if(!databaseController.changePassword(employee.getId(), oldPswd, newPswd)){
            //TODO faces message
        }
    }

    public void testSender(){
        System.out.println("Click");
        databaseController.testSender();
        System.out.println("/Click");
    }

    public String getOldPswd() {
        return oldPswd;
    }

    public void setOldPswd(String oldPswd) {
        this.oldPswd = oldPswd;
    }

    public String getNewPswd() {
        return newPswd;
    }

    public void setNewPswd(String newPswd) {
        this.newPswd = newPswd;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setParkingStatus(List<UISpot> parkingStatus) {
        this.parkingStatus = parkingStatus;
    }

    private void updateParkingStatus(){
        setParkingStatus(databaseController.getParkingStatus(employee));
    }

    public List<UISpot> getParkingStatus() {
        return parkingStatus;
    }

    public String getEmployeeName(){
        return employee.getName();
    }
}