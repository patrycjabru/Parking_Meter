package entities;
import org.hibernate.dialect.Database;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity(name = "parking_spot")
public class ParkingSpot implements Serializable {

    @Id
    @GeneratedValue
    @Column(nullable = false)
    private int id;

    @Column(nullable = false, name = "is_free")
    private Boolean isFree;

    @Column(nullable = true, name = "arrival_time")
    private Timestamp arrivalTime;

    @ManyToOne
    private Zone zone;

    @OneToMany(mappedBy = "parkingSpot", orphanRemoval = true, cascade = CascadeType.REMOVE)
    private List<Ticket> tickets = new ArrayList<Ticket>();

    public ParkingSpot() {
    }

    public ParkingSpot(Boolean isFree, Zone zone) {
        this.isFree = isFree;
        this.zone = zone;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Boolean getFree() {
        return isFree;
    }

    public void setFree(Boolean free) {
        isFree = free;
    }

    public Zone getZone() {
        return zone;
    }

    public void setZone(Zone zone) {
        this.zone = zone;
    }

    public Date getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(Timestamp arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public List<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(List<Ticket> tickets) {
        this.tickets = tickets;
    }
}
