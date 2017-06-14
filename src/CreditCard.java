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
    
   /**
    * Constructor 
    * 
    * This constructor is called the first time a user is created. 
    * 
    * @param cardNumber
    * 					the 16 digit credit card number 
    * @param expirationDate
    * 					the credit card expiration date
    */
   public CreditCard(long cardNumber, Date expirationDate) {
   	this.cardNumber = cardNumber;
   	this.expirationDate = expirationDate;
   }
   
 
    /**
     * Returns the credit card number of the user as type long
     * 
     * @return cardNumber
     * 					the 16 digit credit card number of the customer
     */
    public long getCardNumber() {
    	return cardNumber;
    }
    
    /**
     * Returns the expiration date of the credit card as a Date object
     * 
     * @return expirationDate
     * 					the expiration date of the credit card
     */
    public Date getExpirationDate() {
    	return expirationDate;
    }
    
    /**
     * Returns a string representation of the credit card information
     * 
     * @return ""
     * 					a string representation of the credit card information
     */
    @Override
    public String toString() {
       return ", CC: " + cardNumber + " EXP: " + dateFormat.format(expirationDate);
    }
}
