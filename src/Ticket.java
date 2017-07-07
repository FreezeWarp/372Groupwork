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
        this.date = date; // TODO: should be date of show
    }

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
    
    @Override
    public String toString() {
        return show.getName() +
                ": Customer " + customer.getId() +
                ", Price: " + getPrice() +
                ", " + date +
                "\n";
    }
}
