import java.util.Date;

/**
 * A list of Clients, complying with AccountList.
 *
 * Created by joseph on 12/06/17.
 */
public class ClientList extends AccountList<Client> {
    private static ClientList INSTANCE;

    protected ClientList() { }

    public static ClientList getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new ClientList();
        }

        return INSTANCE;
    }
    
    /**
     * Checks if client has a future show scheduled
     * 
     * @param accountId
     * @return flag true if it can be removed, else false
     */
    public boolean checkShowDates(int accountId) {
    	boolean flag = true;
    	for (Show show : Theater.getInstance().getShowList()) {
    		if (accountId==show.getClientId()) {
    			if (show.getEndDate().after(new Date())) {
    				flag=false;
    			}
    		}
    	}
    	return flag;
    }
}