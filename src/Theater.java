/**
 * Created by joseph on 12/06/17.
 */
public class Theater {
    private static ClientList clientList;

    public static ClientList getClientList() {
        return clientList.INSTANCE;
    }
}