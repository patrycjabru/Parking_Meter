package service;

import org.apache.activemq.artemis.utils.json.JSONObject;
import service.local.MessageStorageLocal;
import service.remote.MessageStorageRemote;

import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Singleton;
import javax.ejb.Stateless;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Local(MessageStorageLocal.class)
@Remote(MessageStorageRemote.class)
@Stateless
public class MessageStorageBean implements MessageStorageLocal, MessageStorageRemote {

    private List<String> messages = new ArrayList<String>();
    private Date lastModified = new Date();

    public void addMessage(String msg){
        if(msg.contains("date")){
            String date = msg.split(":")[1];
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
            try {
                lastModified = formatter.parse(date);
                System.out.println(lastModified);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }else {
            messages.add(msg);
        }
    }

    public List<String> getMessages(int employeeID){
        List<String> res1 = messages.stream().filter(str -> str.contains(employeeID+":")).collect(Collectors.toList());
        messages.removeAll(res1);
        return res1.stream().filter(MessageStorageBean::isAlertValid).collect(Collectors.toList());
    }

    public Date getLastModifiedDate() {
        return lastModified;
    }

    private static Boolean isAlertValid(String alert) {
        String[] alertArray = alert.split(":");
        StringBuffer jsonString = new StringBuffer();
        try {
            URL url = new URL("http://127.0.0.1:8080/MainSystemImpl-1.0" + "/alerts/" + alertArray[1]);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            con.setDoOutput(true);
            con.setRequestProperty("Content-Type", "application/json");
            BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String line;
            while ((line = br.readLine()) != null) {
                jsonString.append(line);
            }
            br.close();
            con.disconnect();
        }catch (Exception e){
            System.out.println("Error: "+e.getMessage());
        }
        return Boolean.parseBoolean(jsonString.toString());
    }

}