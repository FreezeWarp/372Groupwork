import java.util.Date;

/**
 * Created by Joseph T. Parsons on 13/06/17.
 * Modified by Cory Stadther
 */
public class ShowList extends SingletonMap<Integer, Show> {
	 /* Singleton Stuff */
	/**
     * The singleton instance.
     */
    private static ShowList INSTANCE;

    protected ShowList() { }

    public static ShowList getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new ShowList();
        }

        return INSTANCE;
    }

    /**
     * Adds a new show to the ShowList.
     *
     * @param show The show to be added 
     * 
     * @return nothing
     */
    public void addShow(Show show) {
        addEntry(show);
    }

    /**
     * Checks if the date interferes with another date in ShowList
     *
     * @param The Date to start the show
     * @param The Date to end the show
     *
     * @return true if the date is valid, else false
     */
    public boolean validShowDate(Date start, Date end) {
        for (Show show : Theater.getInstance().getShowList()) {
            if ( // our dates conflict if they:
               (start.after(show.getStartDate()) && start.before(show.getEndDate())) // start during another show
               || (end.after(show.getStartDate()) && end.before(show.getEndDate())) // or end during another show
            ) {
                return false; // returns false if any shows conflict
            }
        }
        return true; // returns true when no shows conflict
    }

    /**
     * Checks if client has a future show scheduled
     *
     * @param The account to be checked
     * 
     * @return true if it can be removed, else false
     */
    public boolean checkShowDates(Account account) {
        for (Show show : this) {
            if (account.getId() == show.getClient().getId()) {
                if (show.getEndDate().after(new Date())) {
                    return false;
                }
            }
        }

        return true;
    }
}
