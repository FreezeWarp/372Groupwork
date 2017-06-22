/**
 * Created by joseph on 22/06/17.
 */
public class CreditCardExpiredException extends Exception {
    CreditCardExpiredException() {
        super("The credit card is expired.");
    }
}
