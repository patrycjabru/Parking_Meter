package view;

import service.MessageStorage;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.util.List;

@ManagedBean(name = "notificationView")
@SessionScoped
public class NotificationView {

    @EJB(lookup = "java:global/ManagementApp-1.0/MessageStorageBean!service.remote.MessageStorageRemote")
    MessageStorage messageStorageBean;

    List<String> messages;

    @PostConstruct
    public void init(){
        messages = messageStorageBean.getMessages();
    }

    public List<String> getMessages() {
        messages.addAll(messageStorageBean.getMessages());
        return messages;
    }

    public void setMessages(List<String> messages) {
        this.messages = messages;
    }
}
