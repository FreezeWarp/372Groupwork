import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

/**
 * A credit card, with both number and expiration date.
 * (Security code is not currently included.)
 *
 * @author  Eric Fulwiler
 * @version 1.0
 * @since   2017-06-22
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



    /*################################
     * Read-Only Identifiable<Long> Implementation
     *###############################*/
    /**
     * Implements {@link Identifiable#getId()}, returning the show's start date.
     * (This obviously means that, at present, two shows may not have the same start date. Were this requirement dropped, the easiest solution would be to extend IdentifiableInteger in the normal way.)
     *
     * @return the unique ID of the object, the same as {@link Show#startDate}.
     */
    public Long getId() {
        return getCardNumber();
    }


    /**
     * Implements {@link Identifiable#setId(Object)}, but does nothing: we do not allow for ID assignment in Show.
     *
     * @param id The new id for the object; unused.
     */
    public void setId(Long id) { }


    /**
     * Implements {@link Identifiable#nextId(Object)}, but does nothing: we do not allow for ID assignment in Show.
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

    
    @Override
	public int compareTo(CreditCard creditCard) {
		return (int) Long.compare(getCardNumber(), creditCard.getCardNumber()); 
	}

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
     * An exception for when trying to create a {@link CreditCard} with an invalid card number.
     */
    class CreditCardOutOfRangeException extends Exception {
        CreditCardOutOfRangeException() {
            super("The credit card number is out-of-range.");
        }
    }
}