import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by joseph on 26/06/17.
 */
public class Ticket implements Serializable {
    /**
     * The show for the ticket.
     */
    private Show show;

    /**
     * The show date of the ticket.
     */
    private Date date;

    /**
     * The customer/owner of the ticket.
     */
    private Customer customer;

    /**
     * The format to use when displaying ticket dates in {@link Ticket#toString()}.
     */
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");


    /**
     * Create a new Ticket using a show, customer, and date.
     * @param show {@link Ticket#show}.
     * @param customer {@link Ticket#customer}.
     * @param date {@link Ticket#date}.
     */
    public Ticket(Show show, Customer customer, Date date) {
        this.show = show;
        this.customer = customer;
        this.date = date; 
    }


    /**
     * @return The ticket's show date, {@link Ticket#date}.
     */
    public Date getDate() {
        return date;
    }

    /**
     * @return The ticket's customer/owner, {@link Ticket#customer}.
     */
    public Customer getCustomer() {
        return customer;
    }

    /**
     * @return The ticket's show, {@link Ticket#show}.
     */
    public Show getShow() {
        return show;
    }

    /**
     * @return The ticket's price, derived from the show price in {@link Ticket#show}.
     */
    public double getPrice() {
        return show.getTicketPrice();
    }
    
    /**
     * @return A string representation of the ticket type.
     */
    public String getTicketType() {
        return ", Ticket type: Standard Ticket ";
    }
    
    /**
     * @return A string representation concatenating the basic Ticket information.
     */
    @Override
    public String toString() {
        return  show.getName() +
                ": Customer " + customer.getId() +
                ", Price: " + getPrice() +
                ", " + dateFormat.format(date);
    }
}
