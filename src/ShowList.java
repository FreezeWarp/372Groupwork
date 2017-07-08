import java.util.Date;

/**
 * A list of {@link Show}s and associated functionality, complying with {@link SingletonMap}.
 *
 * @author  Cory Stadther
 * @version 2.0
 * @since   2017-06-29
 */
public class ShowList extends SingletonIdentifiableMap<Date, Show> {
    /*################################
     * Singleton-Specific Functionality
     *###############################*/
    /**
     * The global singleton instance of ShowList.
     */
    protected static SingletonMap INSTANCE;


    /**
     * An unused constructor that overrides the default public constructor, preventing ShowList from being initialised outside of getInstance().
     */
    private ShowList() { }


    /**
     * @return The singleton instance of ShowList. It will be initialised, if necessary.
     */
    public static ShowList getInstance() {
        if (INSTANCE == null) {
            synchronized(ShowList.class) {
                if (INSTANCE == null) {
                    INSTANCE = new ShowList();
                }
            }
        }

        return (ShowList) INSTANCE;
    }



    /*################################
     * Alter List Member Functionality
     *###############################*/

    /**
     * Adds a new show to the ShowList.
     *
     * @param show The show to be added.
     */
    public boolean addShow(Show show) throws ShowConflictException {
        if (!validShowDate(show.getStartDate(), show.getEndDate())) {
            throw new ShowConflictException();
        }

        return addEntry(show);
    }


    /**
     * Checks if the date interferes with another date in ShowList.
     *
     * @param start the Date to start the show.
     * @param end the Date to end the show.
     *
     * @return True if the date is valid, false if invalid.
     */
    public boolean validShowDate(Date start, Date end) {
        for (Show show : this.getInstance()) {
            if ( // our dates conflict if:
               show.hasDate(start) // We start during another show.
               || show.hasDate(end) // We end during another show.
               || (start.before(show.getStartDate()) && end.after(show.getEndDate())) // We occur entirely during another show.
            ) {
                return false; // Returns false if any shows conflict.
            }
        }
        return true; // Returns true when no shows conflict.
    }


    /**
     * Checks if client has a future show scheduled.
     *
     * @param accountId the account to be checked.
     *
     * @return True if it can be removed, false if it cannot.
     */
    public static boolean checkShowDates(int accountId) {
        for (Show show : getInstance()) {
            if (accountId == show.getClient().getId()) {
                if (show.getEndDate().after(new Date())) {
                    return false;
                }
            }
        }

        return true;
    }


    /**
     * Get the show that occurs on a specific date.
     *
     * @param date The date the show is occuring on.
     *
     * @return The show occurring on date, or null if none found.
     */
    public static Show getShow(Date date) {
        Show show = getInstance().singletonMap.floorEntry(date).getValue();

        if (show == null) { // Check for null, which happens when the given date is before all other shows.
            return null;
        }
        else if (show.hasDate(date)) { // Make sure the returned show actually includes the specified date; the date could be between shows, or after all of them.
            return show;
        }
        else {
            return null;
        }
    }



    /*################################
     * Exceptions
     *###############################*/
    /**
     * An exception for when trying to add a {@link Show} to {@link ShowList} when a conflicting Show already exists in the ShowList.
     */
    class ShowConflictException extends Exception {
        ShowConflictException() {
            super("The show conflicts with an existing show.");
        }
    }
}
