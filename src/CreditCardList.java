
/**
 * A list of {@link CreditCard}s and associated functionality, complying with {@link SingletonMap<Long, CreditCard>}.
 * Note that we inherit the member modification methods from {@link SingletonMap<Long, CreditCard>}.
 * The primary goal of this particular list is to allow optimised searches of credit cards, especially for detecting duplicate credit cards across customer accouts.`
 *
 * @author  Eric Fulwiler
 * @version 1.0
 * @since   2017-06-28
 */
public class CreditCardList extends SingletonIdentifiableMap<Long, CreditCard> {
    /*################################
     * Singleton-Specific Functionality
     *###############################*/
    /**
     * The global singleton instance of CreditCardList.
     */
    private static CreditCardList INSTANCE;

    /**
     * An unused constructor that overrides the default public constructor, preventing CreditCardList from being initialised outside of getInstance().
     */
    private CreditCardList() { }


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
