import java.util.Date;

/**
 * An enumeration for selecting and generating a specific ticket type.
 *
 * @author  Joseph T. Parsons
 * @version 2.0
 * @since   2017-July-08
 */
public enum TicketType {
    /*################################
     * Ticket Types
     *###############################*/
    /**
     * A regular ticket.
     */
    Ticket {
    },

    /**
     * A ticket sold in advance.
     */
    AdvanceTicket {
        @Override
        public AdvanceTicket getNewTicket(Show show, Customer customer, Date date) {
            return new AdvanceTicket(show, customer, date);
        }
    },

    /**
     * A ticket sold in advance to a student.
     */
    StudentAdvanceTicket {
        @Override
        public StudentAdvanceTicket getNewTicket(Show show, Customer customer, Date date) {
            return new StudentAdvanceTicket(show, customer, date);
        }
    };



    /*################################
     * Constants
     *###############################*/
    /**
     * The percent of ticket sale revenue that goes to the client. (1 = everything, .5 = 50%, etc.)
     */
    public final static double CLIENT_CUT = .5;



    /*################################
     * Ticket Factory
     *###############################*/

    /**
     * Creates a new ticket. If this is considered a "printing" or selling of a ticket, then you should also invoke {@link TicketType#whenTicketSold(Ticket)} after.
     * Note that this method exists to facilitate in creating a ticket instance corresponding to the Enum value.
     *
     * @param show The show the ticket is admitting to.
     * @param customer The customer buying the ticket.
     * @param date The show date for the ticket.
     *
     * @return A new Ticket instance.
     *
     * @throws TicketExpired if the date is in the past.
     */
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
     * (While this would make sense in Ticket itself, I think it makes somewhat more sense for this to be a method of the factory class itself.)
     *
     * @param ticket The ticket that was just generated and should be considered "sold" for the first time.
     */
    public static void whenTicketSold(Ticket ticket) {
        // Add revenue from ticket sale to client account.
        ticket.getShow().getClient().adjustBalance(ticket.getPrice() * CLIENT_CUT);

        // Add ticket object to Customer's ticket collection.
        ticket.getCustomer().addTicket(ticket);

        // Add ticket object to global TicketList. (used for optimal retrieval of tickets on a given date)
        TicketList.getInstance().addTicket(ticket);
    }


    /**
     * Returns a string copy of a receipt that would be printed given a ticket's show and quantity.
     * Other information can be obtained directly from the ticket instance (this is intended to not require such an instance).
     *
     * @param show The show the ticket would belong to.
     * @param quantity The number of tickets being purchased.
     * @return A String "receipt."
     */
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