package nevents;

import database.ParkingSpotDAO;
import entities.ParkingSpot;

import java.util.TimerTask;

public class CheckValidTicketForSpotTimerTask extends TimerTask {
    int spotId;

    public CheckValidTicketForSpotTimerTask(int spotId) {
        this.spotId = spotId;
    }

    public void run() {
        System.out.println("Checking parking spot...");
        ParkingSpot spot = ParkingSpotDAO.getById(spotId);
        if (EventDetectionManager.isAlertValid(spot)) {
            EventDetectionManager manager = new EventDetectionManager();
            manager.alert(spot);
        }
    }
}
