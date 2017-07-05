import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Joseph on 29/06/2017.
 */
public class TicketList extends SingletonMap<Date, List<Ticket>> {
    /*################################
     * Singleton-Specific Functionality
     *###############################*/
    /**
     * The global singleton instance of TicketList.
     */
    protected static SingletonMap INSTANCE;


    /**
     * An unused constructor that overrides the default public constructor, preventing TicketList from being initialised outside of getInstance().
     */
    private TicketList() { }


    /**
     * @return The singleton instance of TicketList. It will be initialised, if necessary.
     */
    public static TicketList getInstance() {
        if (INSTANCE == null) {
            synchronized(TicketList.class) {
                if (INSTANCE == null) {
                    INSTANCE = new TicketList();
                }
            }
        }

        return (TicketList) INSTANCE;
    }



    /*################################
     * Ticket List Functionality
     *###############################*/
    public void addTicket(Ticket ticket) {
        List<Ticket> list = singletonMap.get(ticket.getDate());

        if (list == null) {
            List<Ticket> newList = new LinkedList<Ticket>();
            newList.add(ticket);

            singletonMap.put(ticket.getDate(), newList);
        }
        else {
            list.add(ticket);
        }
    }

    public List<Ticket> getTickets(Date date) {
        return this.getEntry(date);
    }
}
