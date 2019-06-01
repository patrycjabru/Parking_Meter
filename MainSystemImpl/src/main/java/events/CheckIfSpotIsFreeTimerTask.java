package events;

import database.ParkingSpotDAO;
import entities.ParkingSpot;
import service.EventDetectionManager;

import javax.ejb.EJB;
import java.util.TimerTask;

public class CheckIfSpotIsFreeTimerTask extends TimerTask {
    private int spotId;
    private EventDetectionManager eventDetectionManager;

    public CheckIfSpotIsFreeTimerTask(int spotId, EventDetectionManager _event) {
        this.spotId = spotId;
        this.eventDetectionManager = _event;
    }

    public void run() {
        System.out.println("Checking parking spot..."+spotId+"event: "+ eventDetectionManager);
        ParkingSpot spot = ParkingSpotDAO.getById(spotId);
        if (eventDetectionManager.isAlertValid(spot)) {
            eventDetectionManager.alert(spot);
        }
    }
}
