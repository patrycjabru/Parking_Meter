package entities;
import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


@Entity(name = "zone")
public class Zone implements Serializable {

    @Id
    @GeneratedValue
    @Column(nullable = false)
    private int id;

    @OneToMany(mappedBy = "zone", orphanRemoval = true, cascade = CascadeType.REMOVE)
    private List<ParkingSpot> parkingSpots = new ArrayList<ParkingSpot>();

    @OneToOne
    @JoinColumn(nullable = true, name = "employee_id")
    private Employee employee;

    public Zone() {
    }

    public Zone(Employee employee) {
        this.employee = employee;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
}
