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


    /**
     * Removes a {@link Client} entry from the ClientList.
     *
     * @param clientId The ID of the client object.
     *
     * @return True on success, false on failure.
     *
     * @throws ClientListOngoingShowsException if the client has existing shows registered in ShowList, and thus cannot be removed from the ClientList.
     */
    public boolean removeClient(int clientId) throws ClientListOngoingShowsException {
        if (!ShowList.getInstance().checkShowDates(clientId)) {
            throw new ClientListOngoingShowsException();
        }

        return super.removeAccount(clientId);
    }

    /**
     * Disables the inherited removeAccount method, as removeClient must be used instead to ensure that all validation criteria are met prior to removal.
     *
     * @param accountId Dummy variable.
     *
     * @return none; the UnsupportedOperationException will always be thrown.
     *
     * @throws UnsupportedOperationException always.
     */
    @Override
    public boolean removeAccount(int accountId) {
        throw new UnsupportedOperationException("removeClient must be used instead of removeAccount when working with ClientList.");
    }



    /*################################
     * Exceptions
     *###############################*/
    /**
     * An exception for when trying to remove a {@link Client} from {@link ClientList} when the client has future {@link Show}s and thus cannot be removed.
     */
    public class ClientListOngoingShowsException extends Exception {
        ClientListOngoingShowsException () {
            super("The client cannot be removed if it has shows ongoing.");
        }
    }
}