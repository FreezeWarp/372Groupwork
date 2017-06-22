import java.util.Date;

/**
 * A list of {@link Show}s and associated functionality, complying with {@link SingletonMap}.
 *
 * @author  Cory Stadther
 * @version 1.0
 * @since   2017-06-22
 */
public class ShowList extends SingletonMap<Integer, Show> {
    /*################################
     * Singleton-Specific Functionality
     *###############################*/

    /**
     * The global singleton instance of ShowList. It can be initialised by {@link ShowList#getInstance()}, if needed.
     */
    private static ShowList INSTANCE;


    /**
     * An unused constructor that overrides the default public constructor, preventing ShowList from being initialised outside of getInstance().
     */
    protected ShowList() { }


    /**
     * @return The singleton instance of ShowList. It will be initialised, if necessary.
     */
    public static ShowList getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new ShowList();
        }

        return INSTANCE;
    }



    /*################################
     * Alter List Member Functionality
     *###############################*/

    /**
     * Adds a new show to the ShowList.
     *
     * @param show The show to be added 
     */
    public void addShow(Show show) throws ShowConflictException {
        if (!validShowDate(show.getStartDate(), show.getEndDate())) {
            throw new ShowConflictException();
        }

        addEntry(show);
    }


    /**
     * Checks if the date interferes with another date in ShowList
     *
     * @param start the Date to start the show
     * @param end the Date to end the show
     *
     * @return True if the date is valid, false if invalid
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
     * @param account the account to be checked
     *
     * @return True if it can be removed, false if it cannot
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
