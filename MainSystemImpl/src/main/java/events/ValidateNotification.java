package events;

import database.ParkingSpotDAO;
import entities.ParkingSpot;

import java.util.Calendar;
import java.util.Date;

public class ValidateNotification {
    public static boolean isAlertValid(ParkingSpot spot) {
        if (spot.getFree())
            return false;

        if (ParkingSpotDAO.checkIfPaid(spot))
            return false;

        long ONE_MINUTE_IN_MILLIS = 60000;
        Calendar date = Calendar.getInstance();
        long timeInMillis = date.getTimeInMillis();
        Date afterSubtractingFiveMins = new Date(timeInMillis - (5 * ONE_MINUTE_IN_MILLIS-1));

        if (spot.getArrivalTime().before(afterSubtractingFiveMins))
            return true;

        return false;
    }
}
