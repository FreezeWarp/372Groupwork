/**
 * Created by joseph on 26/06/17.
 */
public class Ticket {
    private Show show;
    private double price;

    public Ticket(Show show) {
        this.show = show;
    }

    public double getPrice() {
        return show.getTicketPrice();
    }
}
