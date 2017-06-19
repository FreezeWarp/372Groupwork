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
}
