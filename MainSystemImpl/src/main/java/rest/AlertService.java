package rest;

import database.ParkingSpotDAO;
import entities.ParkingSpot;
import events.EventDetectionManager;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

@Path("/alerts")
public class AlertService {
    @GET
    @Produces("application/json")
    @Path("/{spot}")
    public Response isAlertValid(@PathParam("spot") int spotId) {
        ParkingSpot spot = ParkingSpotDAO.getById(spotId);
        if (spot == null)
            return Response.status(404).entity("Parking spot with id " + spotId + " has not been found").build();
        boolean isValid = EventDetectionManager.isAlertValid(spot);
        return Response.status(200).entity(isValid).build();
    }
}
