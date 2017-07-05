/**
 * Created by joseph on 26/06/17.
 */
public class StudentAdvanceTicket extends AdvanceTicket {
    public StudentAdvanceTicket(Show show, Customer customer) {
        super(show, customer);
    }

    public double getPrice() {
        return super.getPrice() * .5;
    }
}
