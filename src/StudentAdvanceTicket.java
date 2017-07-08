import java.io.Serializable;
import java.util.Date;

/**
 * Created by joseph on 26/06/17.
 */
public class StudentAdvanceTicket extends AdvanceTicket implements Serializable{
    public StudentAdvanceTicket(Show show, Customer customer, Date date) {
        super(show, customer, date);
    }

    public double getPrice() {
        return super.getPrice() * .5;
    }
    
    /**
     * @return a string representation concatenating the basic Ticket information, plus a notice informing buyers they must show a student id at the show.
     */
    @Override
    public String toString() {
        return  super.toString() + ", Must show valid student id.";
    }
}
