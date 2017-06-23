import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * A theater show, with an associated Client, name, and start and end Dates.
 *
 * @author  Cory Stadther
 * @version 1.0
 * @since   2017-06-22
 */
public class Show extends IdentifiableInteger implements Serializable {
    /**
     * The client who owns the IP rights to the show.
     */
    private Client client;

    /**
     * The name of the show.
     */
    private String name;

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
    public Show(Client client, String name, Date startDate, Date endDate) throws ShowDateMismatchException {
        this.client = client;
        this.name = name;
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
     * @return a string representation concatenating the basic Show information
     */
    @Override
    public String toString() {
        return id +
                ": Client " + client.getId() +
                ", " + name +
                " " + showDateFormat.format(startDate)+
                "-" + showDateFormat.format(endDate);
    }
}
