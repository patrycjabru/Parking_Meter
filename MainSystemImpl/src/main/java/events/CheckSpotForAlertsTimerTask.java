package events;

import database.ParkingSpotDAO;
import entities.ParkingSpot;
import service.AlertManager;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimerTask;

public class CheckSpotForAlertsTimerTask extends TimerTask {
    private int spotId;
    private AlertManager alertManager;

    public CheckSpotForAlertsTimerTask(int spotId, AlertManager alertManager) {
        this.spotId = spotId;
        this.alertManager = alertManager;
    }

    public void run() {
        System.out.println("Checking parking spot "+spotId);
        ParkingSpot spot = ParkingSpotDAO.getById(spotId);
        if (ValidateNotification.isAlertValid(spot)) {
            alertManager.alert(spot);
        }
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
        String dateString = formatter.format(date);
        alertManager.send("date:"+dateString);
    }
}
