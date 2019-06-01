package events;

import entities.ParkingSpot;
import entities.Ticket;
import msg.MDBSender;
import service.local.EventDetectionManagerLocal;
import service.remote.EventDetectionManagerRemote;

import javax.annotation.Resource;
import javax.ejb.*;
import javax.inject.Inject;
import javax.jms.JMSContext;
import javax.jms.Queue;
import javax.jms.TextMessage;
import java.util.Calendar;
import java.util.Date;
import java.util.Timer;


@Local(EventDetectionManagerLocal.class)
@Remote(EventDetectionManagerRemote.class)
@Singleton
public class AEventDetectionManagerBean implements EventDetectionManagerLocal, EventDetectionManagerRemote {

    @EJB
    MDBSender mdbSender;

    public void scheduleTicketCheck(Ticket ticket) {
        Timer timer = new Timer();
        timer.schedule(new CheckValidTicketForSpotTimerTask(ticket.getParkingSpot().getId(),this), ticket.getEndTime());
    }

    public void scheduleSpotCheck(int spotId)  {
        Timer timer = new Timer();

//        long ONE_MINUTE_IN_MILLIS=60000; //TODO uncomment
        long ONE_MINUTE_IN_MILLIS=200;
        Calendar date = Calendar.getInstance();
        long timeInMillis = date.getTimeInMillis();
        Date afterAddingFiveMins = new Date(timeInMillis + (5 * ONE_MINUTE_IN_MILLIS));

        timer.schedule(new CheckIfSpotIsFreeTimerTask(spotId, this), afterAddingFiveMins);
    }

    public void alert(ParkingSpot spot) {
        System.out.println("Detected unpaid parking spot!");
        int employeeId = spot.getZone().getEmployee().getId();
        mdbSender.sendMessage(employeeId+":"+spot.getId());
    }

    public boolean isAlertValid(ParkingSpot spot) {
        return true;//TODO remove this line


//        if (spot.getFree())
//            return false;
//
//        if (ParkingSpotDAO.checkIfPaid(spot))
//            return false;
//
//        long ONE_MINUTE_IN_MILLIS = 60000;
//        Calendar date = Calendar.getInstance();
//        long timeInMillis = date.getTimeInMillis();
//        Date afterSubtractingFiveMins = new Date(timeInMillis - (5 * ONE_MINUTE_IN_MILLIS-1));
//
//        if (spot.getArrivalTime().before(afterSubtractingFiveMins))
//            return true;
//
//        return false;
    }
}
