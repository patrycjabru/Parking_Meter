package service;

import java.util.Date;
import java.util.List;

public interface MessageStorage {

    void addMessage(String msg);
    List<String> getMessages(int employeeID);
    Date getLastModifiedDate();
}
