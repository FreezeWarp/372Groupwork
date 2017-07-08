import java.util.Date;

/**
 * Created by joseph on 26/06/17.
 */
public enum TicketType {
    Ticket {
    },
    AdvanceTicket {
        @Override
        public AdvanceTicket getNewTicket(Show show, Customer customer, Date date) {
            return new AdvanceTicket(show, customer, date);
        }
    },
    StudentAdvanceTicket {
        @Override
        public StudentAdvanceTicket getNewTicket(Show show, Customer customer, Date date) {
            return new StudentAdvanceTicket(show, customer, date);
        }
    };

    public Ticket getNewTicket(Show show, Customer customer, Date date) {
        return new Ticket(show, customer, date);
    }

    /**
     * Performs the data updates that should occur whenever a ticket object is "sold."
     *
     * @param ticket The ticket that was just generated and should be considered "sold" for the first time.
     */
    public static void whenTicketSold(Ticket ticket) {
        // Add revenue from ticket sale to client account.
        ticket.getShow().getClient().adjustBalance(ticket.getPrice());

        // Add ticket object to Customer's ticket collection
        ticket.getCustomer().addTicket(ticket);

        // Add ticket object to global TicketList (used for optimal retrieval of tickets on a given date)
        TicketList.getInstance().addTicket(ticket);
    }
}