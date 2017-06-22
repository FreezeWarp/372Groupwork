import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * A credit card, with both number and expiration date.
 * (Security code is not currently included.)
 *
 * @author  Eric Fulwiler
 * @version 1.0
 * @since   2017-06-22
 */
public class CreditCard implements Serializable {
    /**
     * The 16-digit card number.
     */
    private long cardNumber;

    /**
     * The expiration date of the card.
     */
    private Date expirationDate;

    /**
     * The format to use when displaying the expiration date in {@link CreditCard#toString()}.
     */
    private final SimpleDateFormat dateFormat = new SimpleDateFormat("MM/yyyy");


    /**
     * @param cardNumber the 16 digit credit card number
     * @param expirationDate the credit card expiration date
     */
    public CreditCard(long cardNumber, Date expirationDate) throws CreditCardExpiredException, CreditCardOutOfRangeException {
        setCardNumber(cardNumber);
        setExpirationDate(expirationDate);
    }


    private void setExpirationDate(Date expirationDate) throws CreditCardExpiredException {
        if (expirationDate.before(new Date())) {
            throw new CreditCardExpiredException();
        }
        else {
            this.expirationDate = expirationDate;
        }
    }

    private void setCardNumber(long cardNumber) throws CreditCardOutOfRangeException {
        if (cardNumber < 0L
                || cardNumber > 9999999999999999L) {
            throw new CreditCardOutOfRangeException();
        }
        else {
            this.cardNumber = cardNumber;
        }
    }


    /**
     * @return the 16 digit credit card number, {@link CreditCard#cardNumber}
     */
    public long getCardNumber() {
        return cardNumber;
    }

    /**
     * @return the expiration date of the credit card, {@link CreditCard#expirationDate}
     */
    public Date getExpirationDate() {
        return expirationDate;
    }


    /**
     * @return a string representation of the credit card information
     */
    @Override
    public String toString() {
        return ", CC: " + cardNumber + " EXP: " + dateFormat.format(expirationDate);
    }
}