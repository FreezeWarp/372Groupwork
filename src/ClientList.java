import java.util.HashMap;
import java.util.Iterator;

/**
 * Created by joseph on 12/06/17.
 */
public enum ClientList implements Iterable<Client> {
    INSTANCE;

    private static HashMap<Integer, Client> clientList = new HashMap<Integer, Client>();
    private static int lastClientId = 0;

    public Iterator<Client> iterator() {
        return clientList.values().iterator();
    }

    public static void addClient(Client client) {
        clientList.put(client.getId(), client);
    }

    public static boolean removeClient(int clientId) {
        if (clientList.containsKey(clientId)) {
            clientList.remove(clientId);
            return true;
        }
        else {
            return false;
        }
    }

    public static int getNewClientId() {
        return lastClientId++;
    }
}