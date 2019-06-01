package service;

import entities.ParkingSpot;
import entities.Ticket;

public interface EventDetectionManager {
    void alert(ParkingSpot spot);
    boolean isAlertValid(ParkingSpot spot);
    void scheduleSpotCheck(int spotId);
    void scheduleTicketCheck(Ticket ticket);
}
