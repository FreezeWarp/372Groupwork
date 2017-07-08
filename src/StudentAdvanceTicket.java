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
     * @return a string representation of the ticket type
     */
    public String getTicketType() {
        return ", Ticket type: Student Advance Ticket ";
    }
}
