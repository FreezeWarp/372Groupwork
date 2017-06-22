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
    private Client client;
    private String name;
    private Date startDate;
    private Date endDate;
    private final SimpleDateFormat showDateFormat = new SimpleDateFormat("MM/dd/yyyy");

    /**
     * @param client the client hosting the show
     * @param name the name of the show
     * @param startDate the date the play showings first begin
     * @param endDate the date the play showings end
     */
    public Show(Client client, String name, Date startDate, Date endDate) {
        this.client = client;
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
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
