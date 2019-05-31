package events;

import database.ParkingSpotDAO;
import entities.ParkingSpot;

import java.util.Calendar;
import java.util.Date;
import java.util.TimerTask;

public class CheckValidTicketForSpotTimerTask extends TimerTask {
    int spotId;

    public CheckValidTicketForSpotTimerTask(int spotId) {
        this.spotId = spotId;
    }

    public void run() {
        System.out.println("Checking parking spot...");
        ParkingSpot spot = ParkingSpotDAO.getById(spotId);

        if (spot.getFree())
            return;

        if (ParkingSpotDAO.checkIfPaid(spot))
            return;

        long ONE_MINUTE_IN_MILLIS=60000;
        Calendar date = Calendar.getInstance();
        long timeInMillis = date.getTimeInMillis();
        Date afterAddingFiveMins = new Date(timeInMillis + (5 * ONE_MINUTE_IN_MILLIS));

        if (spot.getArrivalTime().before(afterAddingFiveMins)) {
            System.out.println("Detected unpaid parking spot!");
            //TODO: Call queue sender
        }
    }
}
