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
     * @param show The show to be added 
     */
    public boolean addShow(Show show) throws ShowConflictException {
        if (!validShowDate(show.getStartDate(), show.getEndDate())) {
            throw new ShowConflictException();
        }

        return addEntry(show);
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
        for (Show show : this.getInstance()) {
            if ( // our dates conflict if:
                // TODO: also conflict if a date is shared (not before or after)
               (start.after(show.getStartDate()) && start.before(show.getEndDate())) // start during another show
               || (end.after(show.getStartDate()) && end.before(show.getEndDate())) // ends during another show
               || (start.before(show.getStartDate()) && end.after(show.getEndDate())) // or occurs entirely during another show
            ) {
                return false; // returns false if any shows conflict
            }
        }
        return true; // returns true when no shows conflict
    }


    /**
     * Checks if client has a future show scheduled
     *
     * @param accountId the account to be checked
     *
     * @return True if it can be removed, false if it cannot
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


    public static Show getShow(Date date) {
        return (Show) getInstance().singletonMap.floorEntry(date);
    }

        /**
         * Checks if a show exists for a given date
         *
         * @param date the date to be checked
          *
          * @return client the show belongs to
          */
    public Client checkShowForTicketSales(Date date) {
        for (Show show : Theater.getShowList()) {
            if (date.after(show.getStartDate()) && date.before(show.getEndDate())) {
                return show.getClient(); // I'm not really sure what we want returned here
                //returning client because balance needs to be updated anyways
            }
        }
        return null; //if no show exists during this date
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
