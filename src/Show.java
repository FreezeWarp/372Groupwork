import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by joseph on 12/06/17.
 */
public class Show implements Serializable {
	private int id;
    private String name;
    private Date startDate;
    private Date endDate;
    private final SimpleDateFormat showDateFormat = new SimpleDateFormat("MM/dd/yyyy");
    
    public Show(int id, String name, Date startDate, Date endDate) {
        this.id = id;
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
    }

	/** Gets the id of a show
	 * 
	 * @return id
	 */
	public int getId() {
	    return id;
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
                ": " + name +
                " " + showDateFormat.format(startDate)+
                "-" + showDateFormat.format(endDate);
    }
}
