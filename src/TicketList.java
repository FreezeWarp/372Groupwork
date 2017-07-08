import java.util.Collection;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Joseph on 29/06/2017.
 */
public class TicketList extends SingletonMap<Date, Collection<Ticket>> {
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
    /**
     * Adds a ticket to the ticket list.
     * @param ticket The ticket to add.
     */
    public void addTicket(Ticket ticket) {
        Collection<Ticket> list = singletonMap.get(ticket.getDate());

        if (list == null) {
            List<Ticket> newList = new LinkedList<Ticket>();
            newList.add(ticket);

            singletonMap.put(ticket.getDate(), newList);
        }
        else {
            list.add(ticket);
        }
    }


    /**
     * Gets the list of tickets so far sold for a given show date, or null if none yet sold.
     * @return List<Ticket> The list of Tickets of a given date.
     */
    public Collection<Ticket> getTicketsOn(Date date) {
        if (getInstance().getEntry(date) == null) {
            return null;
        }
        else {
            return getInstance().getEntry(date);
        }
    }
}
