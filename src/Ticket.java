import java.util.Date;

/**
 * Created by joseph on 26/06/17.
 */
public class Ticket {
    private Show show;
    private Date date;
    private Customer customer;
    private double price;

    public Ticket(Show show) {
        this.show = show;
        this.date = new Date();
    }

    public double getPrice() {
        return show.getTicketPrice();
    }
    public Date getDate() {
        return date;
    }
    public Customer getCustomer() {
        return customer;
    }
}
