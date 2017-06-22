/**
 * An exception for when trying to create a {@link CreditCard} with an invalid card number.
 *
 * @author  Joseph Parsons
 * @version 1.0
 * @since   2017-06-22
 */
public class CreditCardOutOfRangeException extends Exception {
    CreditCardOutOfRangeException() {
        super("The credit card number is out-of-range.");
    }
}
