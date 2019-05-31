package service;

import java.util.List;

public interface MessageStorage {

    void addMessage(String msg);
    List<String> getMessages();
}
