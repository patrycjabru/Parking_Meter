import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@ManagedBean
@RequestScoped
public class SensorBean {
    public void setAsOccupied(int spot_id) {
        SoapClient.setAsOccupied(spot_id);

    }
    public void setAsFree(int spot_id) {
        SoapClient.setAsFree(spot_id);
    }
}
