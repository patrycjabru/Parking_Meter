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
    private MDBSender mdbSender;
    private Timer timer = new Timer();

    public void scheduleTicketCheck(Ticket ticket) {
        timer.schedule(new CheckSpotForAlertsTimerTask(ticket.getParkingSpot().getId(),this), ticket.getEndTime());
    }

    public void scheduleSpotCheck(int spotId)  {
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
