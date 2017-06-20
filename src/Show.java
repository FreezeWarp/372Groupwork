import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by joseph on 12/06/17.
 */
public class Show extends IdentifiableInteger implements Serializable {
	private Client client;
    private String name;
    private Date startDate;
    private Date endDate;
    private final SimpleDateFormat showDateFormat = new SimpleDateFormat("MM/dd/yyyy");
    
    public Show(Client client, String name, Date startDate, Date endDate) {
    	this.client = client;
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
    }
    
	/** Gets the clientId of a show
	 * 
	 * @return clientId
	 */
	public Client getClient() {
	    return client;
	}
    
	/** Gets the Name of a show
	 * 
	 * @return name
	 */
	public String getName() {
	    return name;
	}
	
	/** Gets the Start Date of a show
	 * 
	 * @return startDate
	 */
	public Date getStartDate() {
		return startDate;
	}
	
	/** Gets the End Date of a show
	 * 
	 * @return endDate
	 */
	public Date getEndDate() {
		return endDate;
	}
	
    @Override
    public String toString() {
        return id +
        		": Client " + client.getId() +
                ", " + name +
                " " + showDateFormat.format(startDate)+
                "-" + showDateFormat.format(endDate);
    }
}
