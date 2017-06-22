/**
 * An exception for when trying to create a {@link CreditCard} with an expiration date in the past.
 *
 * @author  Joseph Parsons
 * @version 1.0
 * @since   2017-06-22
 */
public class CreditCardExpiredException extends Exception {
    CreditCardExpiredException() {
        super("The credit card is expired.");
    }
}
