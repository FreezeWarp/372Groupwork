/**
 * A list of Customers, complying with AccountList.
 *
 * Created by Joseph T. Parsons on 12/06/17.
 */
public class CustomerList extends AccountList<Customer> {
	 /* Singleton Stuff */
    private static CustomerList INSTANCE;

    protected CustomerList() { }

    public static CustomerList getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new CustomerList();
        }

        return INSTANCE;
    }
}