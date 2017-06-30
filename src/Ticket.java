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

    public void adjustClient() {

        show.getClient().adjustBalance(show.getTicketPrice() * .5);
        System.out.println("Client balance is now $"+ show.getClient().getBalance());
    }

    public void adjustAdvanceClient() {

        show.getClient().adjustBalance(show.getTicketPrice() *.7 * .5);
        System.out.println("Client balance is now $"+ show.getClient().getBalance());
    }

    public void adjustStudentAdvanceClient() {

        show.getClient().adjustBalance(show.getTicketPrice()*.5 * .5);
        System.out.println("Client balance is now $"+ show.getClient().getBalance());
    }
}
