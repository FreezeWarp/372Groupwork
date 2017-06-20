/**
 * A list of Clients, complying with AccountList.
 *
 * Created by joseph on 12/06/17.
 */
public class ClientList extends AccountList<Client> {
    private static ClientList INSTANCE;

    protected ClientList() { }

    public static ClientList getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new ClientList();
        }

        return INSTANCE;
    }
}