package utils;

import entities.ParkingSpot;
import entities.Ticket;
import entities.UISpot;

import java.sql.Timestamp;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class ParkingSpotsToUIConverter {
    public static List<UISpot> convertSpotsToUI(List<ParkingSpot> spots) {
        List<UISpot> uiSpots= new LinkedList<UISpot>();
        for (ParkingSpot s:spots) {
            UISpot uiSpot = new UISpot();
            uiSpot.setId(s.getId());
            uiSpot.setZoneId(s.getId());
            uiSpot.setFree(s.getFree());
            boolean isPaid = checkIfPaid(s);
            uiSpot.setPaid(isPaid);
            uiSpots.add(uiSpot);
        }
        return uiSpots;
    }

    private static boolean checkIfPaid(ParkingSpot spot) {
        List<Ticket> tickets = spot.getTickets();
        Date now = new Date();
        for (Ticket t:tickets) {
            System.out.println(t.getEndTime() +" : "+ now);
            if (t.getEndTime().after(now))
                return true;
        }
        return false;
    }
}