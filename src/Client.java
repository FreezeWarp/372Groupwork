import java.io.Serializable;

/**
 * A Theater Client's Account.
 *
 * Created by joseph on 12/06/17.
 */
public class Client extends Account implements Serializable {
    public Client(String name, String address, long phoneNumber) {
        super(Theater.getInstance().getClientList().getNewAccountId(), name, address, phoneNumber);
    }
    
    @Override
    public String toString() {
        
       return super.toString() + ", " + getBalance();
    }
}