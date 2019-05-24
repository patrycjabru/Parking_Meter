import com.google.gson.Gson;
import org.json.JSONObject;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;


@ManagedBean
@RequestScoped
public class ParkingBean {
    String baseUrl = "http://127.0.0.1:8080/MainSystemImpl-1.0";

    public String buyTicket(int spotId, int durationInHours) throws IOException {
        JSONObject json = new JSONObject();
        json.put("spotId",spotId);
        json.put("durationInHours",durationInHours);

        URL url = new URL(baseUrl + "/tickets");
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("POST");
        con.setDoOutput(true);
        con.setRequestProperty("Content-Type", "application/json");
        OutputStreamWriter writer = new OutputStreamWriter(con.getOutputStream(), "UTF-8");
        writer.write(json.toString());
        writer.close();
        BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
        StringBuffer jsonString = new StringBuffer();
        String line;
        while ((line = br.readLine()) != null) {
            jsonString.append(line);
        }
        br.close();
        con.disconnect();

        return "success";
    }

    public ArrayList<Integer> getSpotsIds() throws IOException {
        URL url = new URL(baseUrl + "/spots");
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");
        con.setDoOutput(true);
        con.setRequestProperty("Content-Type", "application/json");
        BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
        StringBuffer jsonString = new StringBuffer();
        String line;
        while ((line = br.readLine()) != null) {
            jsonString.append(line);
        }
        br.close();
        con.disconnect();

        Gson googleJson = new Gson();
        ArrayList<Double> outputListInDouble = googleJson.fromJson(jsonString.toString(), ArrayList.class);

        ArrayList<Integer> outputList = new ArrayList<Integer>();
        for(Double d : outputListInDouble){
            outputList.add(d.intValue());
        }

        return outputList;
    }
}
