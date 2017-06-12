/**
 * Created by joseph on 12/06/17.
 */
public class Customer extends Account {
    CreditCard creditCard;

    public Customer(String name, String address, long phoneNumber) {
        super(0, name, address, phoneNumber);
    }
}
