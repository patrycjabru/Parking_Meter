import org.json.JSONObject;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import com.fasterxml.jackson.databind.ObjectMapper;

@ManagedBean
@RequestScoped
public class ParkingBean {
    String baseUrl = "http://127.0.0.1:8080/MainSystemImpl-1.0";
    ObjectMapper objectMapper = new ObjectMapper();

    public void buyTicket(int spotId, int durationInHours) throws IOException {
        JSONObject json = new JSONObject();
        json.put("spotId",spotId);
        json.put("durationInHours",durationInHours);

        URL url = new URL(baseUrl + "/tickets");
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("POST");
        con.setDoOutput(true);
        con.setRequestProperty("Content-Type", "application/json");

//        OutputStream os = con.getOutputStream();
//        byte[] input = objectMapper.writeValueAsBytes(json);
//        os.write(input, 0, input.length);
//
//        int responseCode = con.getResponseCode();
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
//        return jsonString.toString();
    }
}
