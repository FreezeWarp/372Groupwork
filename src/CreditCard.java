import java.util.Date;

/**
 * A Credit Card
 *
 * Created by joseph on 12/06/17.
 */
public class CreditCard {
   private long cardNumber;
   private Date expirationDate;
    
    public CreditCard(long cardNumber, Date expirationDate) {
    	this.cardNumber = cardNumber;
    	this.expirationDate = expirationDate;
    }
    
}
