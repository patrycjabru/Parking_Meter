package entities;
import javax.persistence.*;

@Entity(name = "employee")
public class Employee {

    @Id
    @GeneratedValue
    @Column(nullable = false)
    private int id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String password;

    @OneToOne
    @JoinColumn(name = "zone_id")
    private Zone zone;

    @Column(nullable = true)
    private Boolean isAdmin;

    public Employee() {
    }

    public Employee(String name, String password, Zone zone, Boolean isAdmin) {
        this.name = name;
        this.password = password;
        this.zone = zone;
        this.isAdmin = isAdmin;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Zone getZone() {
        return zone;
    }

    public void setZone(Zone zone) {
        this.zone = zone;
    }

    public Boolean getIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(Boolean isAdmin) {
        this.isAdmin = isAdmin;
    }
}
