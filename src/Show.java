import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Joseph T. Parsons on 12/06/17.
 */
public class Show extends IdentifiableInteger implements Serializable {
	private Client client;
    private String name;
    private Date startDate;
    private Date endDate;
    private final SimpleDateFormat showDateFormat = new SimpleDateFormat("MM/dd/yyyy");
    
    /**
     * Constructor for the Show class 
     * 
     * @param client the client hosting the show
     * @param name the name of the show
     * @param startDate the date the play showings first begin
     * @param endDate the date the play showings end
     *                  
     * @return nothing
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
	 * @param nothing
	 * 
	 * @return the client hosting the show
	 */
	public Client getClient() {
	    return client;
	}
    
	/** 
	 * Gets the Name of a show
	 * 
	 * @param nothing
	 * 
	 * @return the name of the show
	 */
	public String getName() {
	    return name;
	}
	
	/**
	 * Gets the Start Date of a show
	 *  
	 * @param nothing
	 * 
	 * @return the date the play begins showing
	 */
	public Date getStartDate() {
		return startDate;
	}
	
	/** 
	 * Gets the End Date of a show
	 * 
	 * @param nothing
	 * 
	 * @return the date the play ends showing
	 */
	public Date getEndDate() {
		return endDate;
	}
	
	/**
     * Overrides the toString method of Object
     * 
     * @param nothing
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
