
/**
 * A list of {@link CreditCard}s and associated functionality, complying with {@link SingletonMap<CreditCard, Customer>}.
 * Note that we inherit the member modification methods from {@link SingletonMap<CreditCard, Customer>}. The goal of this being its own class is to ensure that only {@link CreditCard}s can be added to the list.
 *
 * @author  Eric Fulwiler
 * @version 1.0
 * @since   2017-06-28
 */
public class CreditCardList extends SingletonMap<CreditCard, Customer> {
    /*################################
     * Singleton-Specific Functionality
     *###############################*/
    /**
     * The global singleton instance of CreditCardList.
     */
    protected static CreditCardList INSTANCE;

    /**
     * An unused constructor that overrides the default public constructor, preventing CreditCardList from being initialised outside of getInstance().
     */
    protected CreditCardList() { }


    /**
     * @return The singleton instance of CreditCardList. It will be initialized, if necessary.
     */
    public static CreditCardList getInstance() {
        if (INSTANCE == null) {
            synchronized(CreditCardList.class) {
                if (INSTANCE == null) {
                    INSTANCE = new CreditCardList();
                }
            }
        }

        return (CreditCardList) INSTANCE;
    }
    
   
}
