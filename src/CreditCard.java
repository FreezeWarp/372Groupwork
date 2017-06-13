import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * A Credit Card
 *
 * Created by joseph on 12/06/17.
 */
public class CreditCard {
   private long cardNumber;
   private Date expirationDate;
   private final SimpleDateFormat dateFormat = new SimpleDateFormat("MM/yyyy");
    
    public CreditCard(long cardNumber, Date expirationDate) {
    	this.cardNumber = cardNumber;
    	this.expirationDate = expirationDate;
    }
    
    public long getCardNumber() {
    	return cardNumber;
    }
    
    public Date getExpirationDate() {
    	return expirationDate;
    }
    
    @Override
    public String toString() {
       return ", CC: " + cardNumber + " EXP: " + dateFormat.format(expirationDate);
    }
}
