/**
 * Created by joseph on 26/06/17.
 */
public enum TicketType {
    Ticket {
    },
    AdvanceTicket {
        @Override
        public AdvanceTicket getNewTicket(Show show) {
            return new AdvanceTicket(show);
        }
    },
    StudentAdvanceTicket {
        @Override
        public StudentAdvanceTicket getNewTicket(Show show) {
            return new StudentAdvanceTicket(show);
        }
    };

    public Ticket getNewTicket(Show show) {
        return new Ticket(show);
    }
}