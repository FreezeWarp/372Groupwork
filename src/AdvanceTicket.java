import java.io.Serializable;
import java.util.Date;

/**
 * Created by joseph on 26/06/17.
 */
public class AdvanceTicket extends Ticket implements Serializable{
    public AdvanceTicket(Show show, Customer customer, Date date) {
        super(show, customer, date);
    }

    public double getPrice() {
        return super.getPrice() * .7;
    }
}
