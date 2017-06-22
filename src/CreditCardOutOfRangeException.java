/**
 * Created by joseph on 22/06/17.
 */
public class CreditCardOutOfRangeException extends Exception {
    CreditCardOutOfRangeException() {
        super("The credit card number is out-of-range.");
    }
}
