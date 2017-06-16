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

	public int getId() {
	    return id;
	}
    
	public String getName() {
	    return name;
	}
	
	public Date getStartDate() {
		return startDate;
	}
	
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
