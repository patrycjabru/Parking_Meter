package events;

import database.ParkingSpotDAO;
import entities.ParkingSpot;
import service.EventDetectionManager;

import javax.ejb.EJB;
import java.util.TimerTask;

public class CheckValidTicketForSpotTimerTask extends TimerTask {
    int spotId;
    EventDetectionManager eventDetectionManager;

    public CheckValidTicketForSpotTimerTask(int spotId, EventDetectionManager _event) {
        this.spotId = spotId;
        this.eventDetectionManager = _event;
    }

    public void run() {
        System.out.println("Checking parking spot...");
        ParkingSpot spot = ParkingSpotDAO.getById(spotId);
        if (eventDetectionManager.isAlertValid(spot)) {
            eventDetectionManager.alert(spot);
        }
    }
}
