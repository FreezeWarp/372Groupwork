import java.util.Date;

/**
 * Created by joseph on 26/06/17.
 */
public enum TicketType {
    /*################################
     * Ticket Types
     *###############################*/
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



    /*################################
     * Ticket Factory
     *###############################*/
    public Ticket getNewTicket(Show show, Customer customer, Date date) throws TicketExpired {
        if (date.before(new Date())) {
            throw new TicketExpired();
        }

        return new Ticket(show, customer, date);
    }



    /*################################
     * Ticket Events
     *###############################*/
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


    public String getReceipt(Show show, int quantity) {
        String string = "Price of ticket: $" + show.getTicketPrice();

        if (quantity > 1) {
            string += (System.getProperty("line.separator") + "Final price: $" + show.getTicketPrice() * quantity);
        }

        return string;
    }



    /*################################
     * Exceptions
     *###############################*/
    /**
     * An exception for when trying to create a {@link Show} with an end date that is in the future of the start date.
     */
    class TicketExpired extends Exception {
        TicketExpired() {
            super("The show date specified has already passed, and a ticket cannot be issued.");
        }
    }
}