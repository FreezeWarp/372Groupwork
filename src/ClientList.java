/**
 * A list of {@link Client}s and associated functionality, complying with {@link AccountList}.
 * Note that we inherit the member modification methods from {@link AccountList}. The goal of this being its own class is to ensure that only {@link Client}s can be added to the list.
 *
 * @author  Joseph T. Parsons
 * @version 1.0
 * @since   2017-06-22
 */
public class ClientList extends AccountList<Client> {
    /*################################
     * Singleton-Specific Functionality
     *###############################*/
    /**
     * The global singleton instance of ClientList. It can be initialised by {@link ClientList#getInstance()}, if needed.
     */
    private static ClientList INSTANCE;
    
    /**
     * An unused constructor that overrides the default public constructor, preventing ClientList from being initialised outside of getInstance().
     */
    protected ClientList() { }
    
    /**
     * @return The singleton instance of ClientList. It will be initialised, if necessary.
     */
    public static ClientList getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new ClientList();
        }

        return INSTANCE;
    }
}