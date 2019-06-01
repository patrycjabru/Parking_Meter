package rest;

import database.ParkingSpotDAO;
import database.TicketDAO;
import entities.ParkingSpot;
import entities.Ticket;
import nevents.EventDetectionManager;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Timestamp;
import java.util.Date;

@Path("/tickets")
public class TicketService {

    @POST
    @Consumes("application/json")
    @Path("/")
    public Response addTicket(InputStream inputStream){
        JSONParser jsonParser = new JSONParser();
        JSONObject json;
        try {
             json = (JSONObject)jsonParser.parse(
                    new InputStreamReader(inputStream, "UTF-8"));
        } catch (IOException e) {
            e.printStackTrace();
            return Response.status(500).build();
        } catch (ParseException e) {
            e.printStackTrace();
            return Response.status(500).build();
        }
        Object spotIdObj = json.get("spotId");
        Object durationInHoursObj = json.get("durationInHours");

        String spotIdStr = spotIdObj.toString();
        String durationInHoursStr = durationInHoursObj.toString();

        int spotId = Integer.parseInt(spotIdStr);
        int durationInHours = Integer.parseInt(durationInHoursStr);
        Ticket ticket = new Ticket();

        ParkingSpot spot = ParkingSpotDAO.getById(spotId);
        if (spot==null)
            return Response.status(400).entity("ParkingSpot with given id does not exist").build();

        ticket.setParkingSpot(spot);

        Date date = new Date();
        long time = date.getTime();
        Timestamp tsStart = new Timestamp(time);

        ticket.setStartTime(tsStart);

        Timestamp tsEnd = new Timestamp(time+durationInHours*60*60*1000);

        ticket.setEndTime(tsEnd);
        TicketDAO.add(ticket);

        EventDetectionManager.scheduleTicketCheck(ticket);
        return Response.status(200).build();
    }
}
