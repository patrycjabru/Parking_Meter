package service;

import service.local.MessageStorageLocal;
import service.remote.MessageStorageRemote;

import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Singleton;
import javax.ejb.Stateless;
import java.util.ArrayList;
import java.util.List;

@Local(MessageStorageLocal.class)
@Remote(MessageStorageRemote.class)
@Stateless
public class MessageStorageBean implements MessageStorageLocal, MessageStorageRemote {

    private List<String> messages = new ArrayList<String>();

    public void addMessage(String msg){
        messages.add(msg);
    }

    public List<String> getMessages() {
        List<String> res = new ArrayList<String>(messages);
        messages.clear();
        return res;
    }

}