import java.io.Serializable;
import java.util.Date;

/**
 * A ticket bought in advance.
 *
 * @author  Joseph T. Parsons
 * @version 2.0
 * @since   2017-July-08
 */
public class AdvanceTicket extends Ticket implements Serializable {
    /**
     * The price discount for an advance ticket (1 = no change/full price, .5 = 50% off, etc.)
     */
    private final double ADVANCE_TICKET_DISCOUNT = .7;

    /**
     * Create an AdvanceTicket instance. See {@link Ticket#Ticket(Show, Customer, Date)} for parameters.
     */
    public AdvanceTicket(Show show, Customer customer, Date date) {
        super(show, customer, date);
    }

    /**
     * @return The price of the ticket.
     */
    public double getPrice() {
        return super.getPrice() * ADVANCE_TICKET_DISCOUNT;
    }
    
    /**
     * @return a string representation of the ticket type
     */
    public String getTicketType() {
        return ", Ticket type: Advance Ticket ";
    }
}
