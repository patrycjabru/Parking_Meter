package utils;

import database.ParkingSpotDAO;
import entities.ParkingSpot;
import entities.UISpot;

import java.util.LinkedList;
import java.util.List;

public class ParkingSpotsToUIConverter {
    public static List<UISpot> convertSpotsToUI(List<ParkingSpot> spots) {
        List<UISpot> uiSpots= new LinkedList<UISpot>();
        for (ParkingSpot s:spots) {
            UISpot uiSpot = new UISpot();
            uiSpot.setId(s.getId());
            uiSpot.setZoneId(s.getZone().getId());
            uiSpot.setFree(s.getFree());
            boolean isPaid = ParkingSpotDAO.checkIfPaid(s);
            uiSpot.setPaid(isPaid);
            uiSpots.add(uiSpot);
        }
        return uiSpots;
    }

}