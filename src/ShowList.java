import java.util.Date;

/**
 * Created by joseph on 13/06/17.
 */

// Look at AccountList for how to implement this.
public class ShowList extends SingletonMap<Show> {
    private static ShowList INSTANCE;

    protected ShowList() { }

    public static ShowList getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new ShowList();
        }

        return INSTANCE;
    }

    
    /**
     * Adds a new account to the AccountList.
     *
     * @param account
     */
    public void addShow(Show show) {
        addEntry(getNewKey(), show);
    }


    /**
     * Checks if the date interferes with another date in ShowList
     *
     * @param start The Date to start the show
     * @param end The Date to end the show
     *
     * @return flag true if the date is valid, else false
     */
    public boolean validShowDate(Date start, Date end) {
        for (Show show : Theater.getInstance().getShowList()) {
            if ( // our dates conflict if they:
               (start.after(show.getStartDate()) && start.before(show.getEndDate())) // start during another show
               || (end.after(show.getStartDate()) && end.before(show.getEndDate())) // or end during another show
            ) {
                return false; // return false if any show conflicts
            }
        }

        return true; // return show when no show conflicts
    }
}
