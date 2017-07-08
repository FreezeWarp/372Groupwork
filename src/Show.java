import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * A theater show, with an associated Client, name, and start and end Dates.
 *
 * @author  Cory Stadther
 * @version 2.0
 * @since   2017-July-08
 */
public class Show implements Identifiable<Date>, Serializable {
    /**
     * The client who owns the IP rights to the show.
     */
    private Client client;

    /**
     * The name of the show.
     */
    private String name;

    /**
     * The cost of admittance for the show.
     */
    private double ticketPrice;

    /**
     * The date the show begins on.
     */
    private Date startDate;

    /**
     * The date the show ends on.
     */
    private Date endDate;

    /**
     * The format to use when displaying show dates in {@link Show#toString()}.
     */
    private static final SimpleDateFormat showDateFormat = new SimpleDateFormat("MM/dd/yyyy");


    /**
     * @param client the client hosting the show, {@link Show#client}
     * @param name the name of the show, {@link Show#name}
     * @param startDate the date the play showings first begin, {@link Show#startDate}
     * @param endDate the date the play showings end, {@link Show#endDate}
     */
    public Show(Client client, String name, Date startDate, Date endDate, double ticketPrice) throws ShowDateMismatchException {
        this.client = client;
        this.name = name;
        this.ticketPrice = ticketPrice;
        setDates(startDate, endDate);
    }


    /**
     * @return the client hosting the show, {@link Show#client}
     */
    public Client getClient() {
        return client;
    }

    /**
     * @return the name of the show, {@link Show#name}
     */
    public String getName() {
        return name;
    }

    /**
     * @return the cost of admittance for the show, {@link Show#ticketPrice}
     */
    public double getTicketPrice() {
        return ticketPrice;
    }

    /**
     * @return the date the play begins showing, {@link Show#startDate}
     */
    public Date getStartDate() {
        return startDate;
    }

    /**
     * @return the date the play ends showing, {@link Show#endDate}
     */
    public Date getEndDate() {
        return endDate;
    }

    /**
     * Implements {@link Identifiable#getId()}, returning the show's start date.
     * (This obviously means that, at present, two shows may not have the same start date. Were this requirement dropped, the easiest solution would be to extend IdentifiableInteger in the normal way.)
     *
     * @return the unique ID of the object, the same as {@link Show#startDate}.
     */
    public Date getId() {
        return getStartDate();
    }


    /**
     * Implements {@link Identifiable#setId(Object)}, but does nothing: we do not allow for ID assignment in Show.
     *
     * @param id The new id for the object; unused.
     */
    public void setId(Date id) { }


    /**
     * Implements {@link Identifiable#nextId(Object)}, but does nothing: we do not allow for ID assignment in Show.
     *
     * @param date The previous date.
     *
     * @return A dummy date.
     */
    public Date nextId(Date date) {
        return new Date(0);
    }

    /**
     * Sets both {@link Show#startDate} and {@link Show#endDate}
     * @param startDate The starting date of the show, {@link Show#startDate}.
     * @param endDate The ending date of the show, {@link Show#endDate}
     *
     * @throws ShowDateMismatchException when the ending date is before the starting date.
     */
    private void setDates(Date startDate, Date endDate) throws ShowDateMismatchException {
        if (endDate.before(startDate)) {
            throw new ShowDateMismatchException();
        }

        this.startDate = startDate;
        this.endDate = endDate;
    }


    /**
     * Check whether the Show has a given date as one of its showings.
     *
     * @param date A date to check for the show's happening on.
     * @return True if the Show is occurring on the specified Date, false otherwise.
     */
    public boolean hasDate(Date date) {
        return (getStartDate().before(date) && getEndDate().after(date)) || getStartDate().equals(date) || getEndDate().equals(date);
    }


    /**
     * @return a string representation concatenating the basic Show information
     */
    @Override
    public String toString() {
        return "Client " + client.getId() +
                ", " + name +
                " " + showDateFormat.format(startDate)+
                "-" + showDateFormat.format(endDate);
    }


    /*################################
     * Exceptions
     *###############################*/
    /**
     * An exception for when trying to create a {@link Show} with an end date that is in the future of the start date.
     */
    class ShowDateMismatchException extends Exception {
        ShowDateMismatchException() {
            super("The end date of a show cannot be before its start date.");
        }
    }
}
