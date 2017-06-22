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
     * @param client the client hosting the show
     * @param name the name of the show
     * @param startDate the date the play showings first begin
     * @param endDate the date the play showings end
     */
    public Show(Client client, String name, Date startDate, Date endDate) throws ShowDateMismatchException {
        this.client = client;
        this.name = name;
        setDates(startDate, endDate);
    }


    /**
     * Gets the client who is hosting the show
     *
     * @return the client hosting the show
     */
    public Client getClient() {
        return client;
    }

    /**
     * Gets the name of a show
     *
     * @return the name of the show
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the start Date of a show
     *
     * @return the date the play begins showing
     */
    public Date getStartDate() {
        return startDate;
    }

    /**
     * Gets the end Date of a show
     *
     * @return the date the play ends showing
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
     * Overrides the toString method of Object
     *
     * @return a string representation concatenating basic show information
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
