package service;

import entities.ParkingSpot;
import entities.Ticket;

public interface AlertManager {
    void alert(ParkingSpot spot);
    void scheduleSpotCheck(int spotId);
    void scheduleTicketCheck(Ticket ticket);
    void send(String msg);
}
