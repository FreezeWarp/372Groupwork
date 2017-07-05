/**
 * Created by joseph on 26/06/17.
 */
public enum TicketType {
    Ticket {
    },
    AdvanceTicket {
        @Override
        public AdvanceTicket getNewTicket(Show show, Customer customer) {
            return new AdvanceTicket(show, customer);
        }
    },
    StudentAdvanceTicket {
        @Override
        public StudentAdvanceTicket getNewTicket(Show show, Customer customer) {
            return new StudentAdvanceTicket(show, customer);
        }
    };

    public Ticket getNewTicket(Show show, Customer customer) {
        return new Ticket(show, customer);
    }

    public static void onTicketSale(Ticket ticket) {
        // Add revenue from ticket sale to client account.
        ticket.getShow().getClient().adjustBalance(ticket.getPrice());

        // Add ticket object to Customer's ticket collection
        ticket.getCustomer().addTicket(ticket);

        // Add ticket object to global TicketList (used for optimal retrieval of tickets on a given date)
        TicketList.getInstance().addTicket(ticket);
    }
}