package view;

import entities.Employee;
import service.DatabaseController;
import service.MessageStorage;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import java.security.Principal;
import java.util.List;

@ManagedBean(name = "notificationView")
@SessionScoped
public class NotificationView {

    private Employee employee;

    @EJB(lookup = "java:global/AlertApp-1.0/MessageStorageBean!service.remote.MessageStorageRemote")
    MessageStorage messageStorageBean;

    @EJB(lookup = "java:global/MainSystemImpl-1.0/DatabaseControllerBean!service.remote.DatabaseControllerRemote")
    DatabaseController databaseController;

    List<String> messages;

    @PostConstruct
    public void init(){
        if (employee == null) {
            Principal principal = FacesContext.getCurrentInstance().getExternalContext().getUserPrincipal();
            if (principal != null) {
                employee = databaseController.getEmployeeByName(principal.getName());
            }
        }
        messages = messageStorageBean.getMessages(employee.getId());
    }

    public List<String> getMessages() {
        messages.addAll(messageStorageBean.getMessages(employee.getId()));
        return messages;
    }

    public void setMessages(List<String> messages) {
        this.messages = messages;
    }

    public String getEmployeeName(){
        return employee.getName();
    }
}
