/**
 * Created by joseph on 12/06/17.
 */
public class Client extends Account {
    public Client(String name, String address, long phoneNumber) {
        super(ClientList.getNewClientId(), name, address, phoneNumber);
    }
}