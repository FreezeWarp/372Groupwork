import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

/**
 * A credit card, with both number and expiration date.
 * (Security code is not currently included.)
 *
 * @author  Eric Fulwiler
 * @version 2.0
 * @since   2017-July-08
 */
public class CreditCard implements Identifiable<Long>, Serializable, Comparable<CreditCard> {
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
     * The minimum valid credit card number.
     */
    private static final long CREDIT_CARD_NUMBER_MINIMUM = 0L;

    /**
     * The maximum valid credit card number.
     */
    private static final long CREDIT_CARD_NUMBER_MAXIMUM = 9999999999999999L;



    /**
     * @param cardNumber The 16 digit credit card number, {@link CreditCard#cardNumber}
     * @param expirationDate The credit card expiration date, {@link CreditCard#expirationDate}
     */
    public CreditCard(long cardNumber, Date expirationDate) throws CreditCardExpiredException, CreditCardOutOfRangeException {
        setCardNumber(cardNumber);
        setExpirationDate(expirationDate);
    }

    /**
     * @param expirationDate The credit card expiration date, {@link CreditCard#expirationDate}
     */
    private void setExpirationDate(Date expirationDate) throws CreditCardExpiredException {
        if (expirationDate.before(new Date())) {
            throw new CreditCardExpiredException();
        }
        else {
            this.expirationDate = expirationDate;
        }
    }

    /**
     * @param cardNumber The 16 digit credit card number, {@link CreditCard#cardNumber}
     */
    private void setCardNumber(long cardNumber) throws CreditCardOutOfRangeException {
        if (cardNumber < CREDIT_CARD_NUMBER_MINIMUM
                || cardNumber > CREDIT_CARD_NUMBER_MAXIMUM) {
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



    /*################################
     * Read-Only Identifiable<Long> Implementation
     *###############################*/
    /**
     * Implements {@link Identifiable#getId()}, returning the credit card's 16 digit number.)
     *
     * @return the unique ID of the object, the same as {@link CreditCard#cardNumber}.
     */
    public Long getId() {
        return getCardNumber();
    }


    /**
     * Implements {@link Identifiable#setId(Object)}, but does nothing: we do not allow for ID assignment in CreditCard.
     *
     * @param id The new id for the object; unused.
     */
    public void setId(Long id) { }


    /**
     * Implements {@link Identifiable#nextId(Object)}, but does nothing: we do not allow for ID assignment in CreditCard.
     *
     * @param date The previous date.
     *
     * @return A dummy date.
     */
    public Long nextId(Long date) {
        return 0L;
    }



    /*################################
     * Object Overrides
     *###############################*/
    /**
     * @return a string representation of the credit card information
     */
    @Override
    public String toString() {
        return ", CC: " + cardNumber + " EXP: " + dateFormat.format(expirationDate);
    }

    /**
     * @param creditCard The 16 digit credit card number, {@link CreditCard#cardNumber}. 
     * 
     * @return a negative integer, zero, or a positive integer as this object is less than, equal to, or greater than the specified object.  
     */
    @Override
	public int compareTo(CreditCard creditCard) {
		return (int) Long.compare(getCardNumber(), creditCard.getCardNumber()); 
	}

    /**
     * @param o The object being compared to this current CreditCard object to see if they "are equal."
     * 
     * @return True if the parameter object is equal to the current CreditCard object, false otherwise. 
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CreditCard that = (CreditCard) o;
        return getCardNumber() == that.getCardNumber() &&
                Objects.equals(getExpirationDate(), that.getExpirationDate());
    }



	/*################################
     * Exceptions
     *###############################*/
    /**
     * An exception for when trying to create a {@link CreditCard} with an expiration date in the past.
     */
    class CreditCardExpiredException extends Exception {
        CreditCardExpiredException() {
            super("The credit card is expired.");
        }
    }

    /**
     * An exception for when trying to create a {@link CreditCard} with an invalid card number, {@link CreditCard#cardNumber}.
     */
    class CreditCardOutOfRangeException extends Exception {
        CreditCardOutOfRangeException() {
            super("The credit card number is out-of-range.");
        }
    }
}