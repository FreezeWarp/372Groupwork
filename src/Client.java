import java.io.Serializable;

/**
 * Created by joseph on 12/06/17.
 */
public class Client extends Account implements Serializable {
    public Client(String name, String address, long phoneNumber) {
        super(Theater.getInstance().getClientList().getNewAccountId(), name, address, phoneNumber);
    }
}