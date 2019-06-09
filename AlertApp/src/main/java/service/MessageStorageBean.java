package service;

import service.local.MessageStorageLocal;
import service.remote.MessageStorageRemote;

import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Singleton;
import javax.ejb.Stateless;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Local(MessageStorageLocal.class)
@Remote(MessageStorageRemote.class)
@Stateless
public class MessageStorageBean implements MessageStorageLocal, MessageStorageRemote {

    private List<String> messages = new ArrayList<String>();

    public void addMessage(String msg){
        messages.add(msg);
    }

    public List<String> getMessages(int employeeID) {
        List<String> res = new ArrayList<String>(messages);
//TODO przefiltrować, te wiadomości ktorych empID == employeeID, usunąć je z messages i wysłać zapytanie o nie do REST
//        List<String> res1 = messages.stream().filter(str -> str.contains(employeeID+":")).collect(Collectors.toList());
//        messages.removeAll(res1);
//        res1 -> do RESTA
        messages.clear();
        return res;
    }

}