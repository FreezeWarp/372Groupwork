/**
 * A list of Clients, complying with AccountList.
 *
 * Created by Joseph T. Parsons on 12/06/17.
 */
public class ClientList extends AccountList<Client> {
	 /* Singleton Stuff */
    private static ClientList INSTANCE;

    protected ClientList() { }

    public static ClientList getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new ClientList();
        }

        return INSTANCE;
    }
}