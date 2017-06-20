import java.util.Date;

/**
 * Created by joseph on 13/06/17.
 */

// Look at AccountList for how to implement this.
public class ShowList extends SingletonMap<Integer, Show> {
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
     * @param show
     */
    public void addShow(Show show) {
        addEntry(show);
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


    /**
     * Checks if client has a future show scheduled
     *
     * @param account
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
