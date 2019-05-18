import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "parking_spot")
public class ParkingSpot {

    @Id
    @GeneratedValue
    @Column(nullable = false)
    private int id;

    @Column(nullable = false, name = "is_free")
    private Boolean isFree;

    @ManyToOne
    @JoinColumn(name = "zone_id")
    private Zone zone;

    @OneToMany(mappedBy = "parking_spot", orphanRemoval = true, cascade = CascadeType.REMOVE)
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
}
