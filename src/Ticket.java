import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

/**
 * Created by joseph on 26/06/17.
 */
public class Ticket {
    private Show show;
    private Date date;
    private Customer customer;

    public Ticket(Show show, Customer customer, Date date) {
        this.show = show;
        this.customer = customer;
        this.date = date; 
    }
    
    /**
     * The format to use when displaying ticket dates in {@link Ticket#toString()}.
     */
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");

    public Date getDate() {
        return date;
    }
    public Customer getCustomer() {
        return customer;
    }
    public Show getShow() {
        return show;
    }

    public double getPrice() {
        return show.getTicketPrice();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ticket ticket = (Ticket) o;
        return Double.compare(ticket.getPrice(), getPrice()) == 0 &&
                Objects.equals(show, ticket.show) &&
                Objects.equals(getDate(), ticket.getDate()) &&
                Objects.equals(getCustomer(), ticket.getCustomer());
    }

    public void adjustClientBalance() {
        System.out.println("Client balance is now $"+ show.getClient().getBalance()); // TODO: refactor this line into the invoking method.
    }
    
    /**
     * @return a string representation concatenating the basic Ticket information
     */
    @Override
    public String toString() {
        return  show.getName() +
                ": Customer " + customer.getId() +
                ", Price: " + getPrice() +
                ", " + dateFormat.format(date);
    }
}
