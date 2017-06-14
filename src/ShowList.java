/**
 * Created by joseph on 13/06/17.
 */

// Look at AccountList for how to implement this.
public class ShowList extends SingletonHashmap<Show> {
    private static ShowList INSTANCE;

    protected ShowList() { }

    public static ShowList getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new ShowList();
        }

        return INSTANCE;
    }
}
