package events;

import database.ParkingSpotDAO;
import entities.Employee;
import entities.ParkingSpot;
import msg.MDBSender;

import java.util.Calendar;
import java.util.Date;
import java.util.TimerTask;

public class CheckIfSpotIsFreeTimerTask extends TimerTask {
    private int spotId;

    public CheckIfSpotIsFreeTimerTask(int spotId) {
        this.spotId = spotId;
    }

    public void run() {
        System.out.println("Checking parking spot...");
        ParkingSpot spot = ParkingSpotDAO.getById(spotId);
        if (EventDetectionManager.isAlertValid(spot)) {
            EventDetectionManager.alert(spot);
        }
    }
}
