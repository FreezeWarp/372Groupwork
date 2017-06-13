/**
 * A list of Customers, complying with AccountList.
 *
 * Created by joseph on 12/06/17.
 */
public class CustomerList extends AccountList<Customer> {
    private static CustomerList INSTANCE;

    protected CustomerList() { }

    public static CustomerList getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new CustomerList();
        }

        return INSTANCE;
    }
}