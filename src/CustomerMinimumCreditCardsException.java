/**
 * Created by joseph on 22/06/17.
 */
public class CustomerMinimumCreditCardsException extends Exception {
    CustomerMinimumCreditCardsException() {
        super("The last credit card cannot be removed.");
    }
}
