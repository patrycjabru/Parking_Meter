package utils;

import database.ParkingSpotDAO;
import database.ZoneDAO;
import entities.Zone;

public class Statistic {

    public static Double getPercentOfEmptySpots(){
        return ParkingSpotDAO.getPercentOfEmptySpots();
    }

    public static Double getPercentOfEmptySpotsForZone(int zone_id){
        Zone zone = ZoneDAO.getById(zone_id);
        return ParkingSpotDAO.getPercentOfEmptySpotsForZone(zone);
    }

}
