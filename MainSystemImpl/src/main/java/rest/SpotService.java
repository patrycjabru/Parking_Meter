package rest;

import database.ParkingSpotDAO;
import entities.ParkingSpot;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.LinkedList;
import java.util.List;

@Path("/spots")
public class SpotService {

    @GET
    @Produces("application/json")
    @Path("/")
    public Response getSpotsIds() {
        List<ParkingSpot> spots = ParkingSpotDAO.getAll();
        if (spots == null || spots.size()==0)
            return Response.status(500).entity("Error while getting Parking Spots from Database").build();

        List<Integer> ids = new LinkedList<Integer>();
        for (ParkingSpot s:spots) {
            ids.add(s.getId());
        }
        return Response.status(200).entity(ids).build();
    }
}
