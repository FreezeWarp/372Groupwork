/**
 * A list of {@link Client}s and associated functionality, complying with {@link CustomerList}.
 * Note that we inherit the member modification methods from {@link AccountList}. The goal of this being its own class is to ensure that only {@link Client}s can be added to the list.
 *
 * @author  Eric Fulwiler
 * @version 2.0
 * @since   2017-July-08
 */
public class CustomerList extends AccountList<Customer> {
    /**
     * The global singleton instance of CustomerList.
     */
    protected static CustomerList INSTANCE;

    /**
     * An unused constructor that overrides the default public constructor, preventing CustomerList from being initialised outside of getInstance().
     */
    private CustomerList() { }


    /**
     * @return The singleton instance of CustomerList. It will be initialised, if necessary.
     */
    public static CustomerList getInstance() {
        if (INSTANCE == null) {
            synchronized(ClientList.class) {
                if (INSTANCE == null) {
                    INSTANCE = new CustomerList();
                }
            }
        }

        return INSTANCE;
    }
}