package events;

import database.ParkingSpotDAO;
import entities.ParkingSpot;
import entities.Ticket;

import java.util.Calendar;
import java.util.Date;
import java.util.Timer;

public class EventDetectionManager {
    public static void scheduleTicketCheck(Ticket ticket) {
        Timer timer = new Timer();
        timer.schedule(new CheckValidTicketForSpotTimerTask(ticket.getParkingSpot().getId()), ticket.getEndTime());
    }

    public static void scheduleSpotCheck(int spotId)  {
        Timer timer = new Timer();

        long ONE_MINUTE_IN_MILLIS=60000;
        Calendar date = Calendar.getInstance();
        long timeInMillis = date.getTimeInMillis();
        Date afterAddingFiveMins = new Date(timeInMillis + (5 * ONE_MINUTE_IN_MILLIS));

        timer.schedule(new CheckIfSpotIsFreeTimerTask(spotId), afterAddingFiveMins);
    }
}
