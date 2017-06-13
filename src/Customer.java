/**
 * A Theater Customer's Account.
 *
 * Created by joseph on 12/06/17.
 */
public class Customer extends Account {
    CreditCard creditCard;

    public Customer(String name, String address, long phoneNumber, CreditCard creditCard) {
        super(Theater.getInstance().getCustomerList().getNewAccountId(), name, address, phoneNumber);
        this.creditCard = creditCard;
    }
}