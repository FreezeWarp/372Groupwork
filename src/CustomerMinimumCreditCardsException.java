/**
 * An exception for when trying to remove a {@link CreditCard} from a {@link Customer} when the customer only has one associated {@link CreditCard}.
 *
 * @author  Joseph Parsons
 * @version 1.0
 * @since   2017-06-22
 */
public class CustomerMinimumCreditCardsException extends Exception {
    CustomerMinimumCreditCardsException() {
        super("The last credit card cannot be removed.");
    }
}
