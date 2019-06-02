package events;

import entities.ParkingSpot;
import entities.Ticket;
import msg.MDBSender;
import service.local.AlertManagerLocal;
import service.remote.AlertManagerRemote;

import javax.ejb.*;
import java.util.Calendar;
import java.util.Date;
import java.util.Timer;


@Local(AlertManagerLocal.class)
@Remote(AlertManagerRemote.class)
@Singleton
public class AlertManagerBean implements AlertManagerLocal, AlertManagerRemote {

    @EJB
    MDBSender mdbSender;

    public void scheduleTicketCheck(Ticket ticket) {
        Timer timer = new Timer();
        timer.schedule(new CheckSpotForAlertsTimerTask(ticket.getParkingSpot().getId(),this), ticket.getEndTime());
    }

    public void scheduleSpotCheck(int spotId)  {
        Timer timer = new Timer();

        long ONE_MINUTE_IN_MILLIS=60000;
        Calendar date = Calendar.getInstance();
        long timeInMillis = date.getTimeInMillis();
        Date afterAddingFiveMins = new Date(timeInMillis + (5 * ONE_MINUTE_IN_MILLIS));

        timer.schedule(new CheckSpotForAlertsTimerTask(spotId, this), afterAddingFiveMins);
    }

    public void alert(ParkingSpot spot) {
        System.out.println("Detected unpaid parking spot!");
        int employeeId = spot.getZone().getEmployee().getId();
        mdbSender.sendMessage(employeeId+":"+spot.getId());
    }
}
