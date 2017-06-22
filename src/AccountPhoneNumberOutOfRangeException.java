/**
 * An exception for when trying to create an {@link Account} with an invalid phone number.
 *
 * @author  Joseph Parsons
 * @version 1.0
 * @since   2017-06-22
 */
public class AccountPhoneNumberOutOfRangeException extends Exception {
    AccountPhoneNumberOutOfRangeException() {
        super("The phone number is out-of-range.");
    }
}
