/**
 * Created by joseph on 26/06/17.
 */
public class AdvanceTicket extends Ticket {
    public AdvanceTicket(Show show) {
        super(show);
    }

    public double getPrice() {
        return super.getPrice() * .7;
    }
}
