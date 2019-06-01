package events;

import database.ParkingSpotDAO;
import entities.ParkingSpot;

import java.util.TimerTask;

public class CheckIfSpotIsFreeTimerTask extends TimerTask {
    private int spotId;

    public CheckIfSpotIsFreeTimerTask(int spotId) {
        this.spotId = spotId;
    }

    public void run() {
        System.out.println("Checking parking spot...");
        ParkingSpot spot = ParkingSpotDAO.getById(spotId);
        if (EventDetectionManagerImpl.isAlertValid(spot)) {
            EventDetectionManagerImpl manager = new EventDetectionManagerImpl();
            manager.alert(spot);
        }
    }
}
