import java.io.Serializable;
import java.util.HashMap;
import java.util.Iterator;

/**
 * Created by joseph on 12/06/17.
 */
public class ClientList implements Iterable<Client>, Serializable {
    private HashMap<Integer, Client> clientList = new HashMap<Integer, Client>();
    private int lastClientId = 0;

    public Iterator<Client> iterator() {
        return clientList.values().iterator();
    }

    public void addClient(Client client) {
        clientList.put(client.getId(), client);
    }

    public boolean removeClient(int clientId) {
        if (clientList.containsKey(clientId)) {
            clientList.remove(clientId);
            return true;
        }
        else {
            return false;
        }
    }

    public int getNewClientId() {
        return lastClientId++;
    }

    public HashMap<Integer, Client> getMap() {
        return this.clientList;
    }
}