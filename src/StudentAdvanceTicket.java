import java.io.Serializable;
import java.util.Date;

/**
 * A ticket bought in advance by a student.
 *
 * @author  Joseph T. Parsons
 * @version 2.0
 * @since   2017-July-08
 */
public class StudentAdvanceTicket extends AdvanceTicket implements Serializable {
    /**
     * The price discount for a student advance ticket compared to an advanced ticket (1 = no change/full price, .5 = 50% off, etc.)
     */
    private final double STUDENT_ADVANCE_TICKET_DISCOUNT = .5;

    /**
     * Create a StudentAdvanceTicket instance. See {@link Ticket#Ticket(Show, Customer, Date)} for parameters.
     */
    public StudentAdvanceTicket(Show show, Customer customer, Date date) {
        super(show, customer, date);
    }

    /**
     * @return The price of the ticket.
     */
    public double getPrice() {
        return super.getPrice() * STUDENT_ADVANCE_TICKET_DISCOUNT;
    }
    
    /**
     * @return a string representation of the ticket type
     */
    public String getTicketType() {
        return ", Ticket type: Student Advance Ticket -Must show valid student id ";
    }
}
