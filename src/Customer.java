import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;

/**
 * A Theater Customer's Account.
 *
 * Created by Joseph T. Parsons on 12/06/17.
 * Modified by Eric Fulwiler
 */
public class Customer extends Account {
    private Collection<CreditCard> creditCardList = new ArrayList<CreditCard>();
    private Collection<Ticket> ticketList = new ArrayList<Ticket>();
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");

    /**
     * @param name the name of the customer
     * @param address the address of the customer
     * @param phoneNumber the phone number of the customer
     * @param creditCard the first credit card for the customer
     */
    public Customer(String name, String address, long phoneNumber, CreditCard creditCard) throws AccountPhoneNumberOutOfRangeException, CustomerDuplicateCardException {
        super(name, address, phoneNumber);
        addCreditCard(creditCard);
    }
    

    /**
     * Adds a credit card to the customer's account
     * 
     * @param creditCard the new credit card being added
     * 
     * @return True if the card could be added, False otherwise
     */
    public boolean addCreditCard(CreditCard creditCard) throws CustomerDuplicateCardException {
    	if (CreditCardList.getInstance().hasEntry(creditCard.getCardNumber())) {
    		throw new CustomerDuplicateCardException();
    	}

        return creditCardList.add(creditCard) && Theater.getCreditCardList().addEntry(creditCard);
     
    }


    /**
     * @return the credit card with a given credit card number.
     */
    public CreditCard getCreditCard(long creditCardNumber) {
        CreditCard creditCard = CreditCardList.getInstance().getEntry(creditCardNumber);

        // Only return the chosen credit card if it exists in our customer's own list.
        if (creditCardList.contains(creditCard)) {
            return creditCard;
        }
        else {
            return null;
        }
    }

    
    /**
     * Removes the specified credit card from the customer's account
     * 
     * @param creditCardNumber the 16 digit number of the credit card to be removed
     * 
     * @return True if the card could be removed, False otherwise
     */
    public boolean removeCreditCard(long creditCardNumber) throws CustomerMinimumCreditCardsException {
        if (this.creditCardList.size() <= 1) {
            throw new CustomerMinimumCreditCardsException();
        }
       
        for (CreditCard creditCard : creditCardList) { //NEW IMPLEMENTATION
           if (creditCard.getCardNumber() == creditCardNumber) {
               CreditCardList.getInstance().removeEntry(creditCard.getCardNumber());
            }
        }

        return creditCardList.removeIf((CreditCard creditCard) -> creditCard.getCardNumber() == creditCardNumber) ;
    }

    
    /**
     * Removes all credit cards from the customer's account. To be used when a customer wishes to have their entire account deleted.
     */
    public boolean removeCreditCards() throws CustomerCouldNotDeleteCreditCardsException {
        for (CreditCard creditCard : creditCardList) { 
            CreditCardList.getInstance().removeEntry(creditCard.getCardNumber());
         }
        
        creditCardList.clear(); 
        if (creditCardList.isEmpty())  {
        	return true;
        }
        else {
        	throw new CustomerCouldNotDeleteCreditCardsException();
        }
    }

    /**
     * Adds a ticket.
     *
     * @param t The ticket to add.
     * @return true on success, false on failure
     */
    public boolean addTicket(Ticket t) {
        return ticketList.add(t);
    }


    /**
     * Removes a ticket. If multiple identical tickets exist, only the first will be removed.
     *
     * @param t The ticket to remove.
     * @return true on success, false on failure
     */
    public boolean removeTicket(Ticket t) {
        return ticketList.remove(t);
    }


    /**
     * Gets the entire credit card ArrayList
     * 
     * @return the ArrayList containing credit card objects 
     */
    public Collection<CreditCard> getCreditCardList() {
        return creditCardList;
    }


    /**
     * Overrides the toString method of Account
     * 
     * @return a string representation concatenating basic customer information
     */
    @Override
    public String toString() {
        String creditCardString = "";
        String ticketString = "";
        int count = 0;
        
        for (CreditCard creditCard : creditCardList) {
        	creditCardString += creditCard;
        }
        
        for (Ticket ticket : ticketList) {
        	ticketString += "\n    Ticket #" + count + ": " + ticket.getShow().getName() +
                            ", Price: $" + ticket.getPrice() + ", " + dateFormat.format(ticket.getDate());
        	count++;
        }
        
        return super.toString() + creditCardString + ticketString;
    }


    /*################################
     * Exceptions
     *###############################*/
    /**
     * An exception for when trying to remove a {@link CreditCard} from a {@link Customer} when the customer only has one associated {@link CreditCard}.
     */
    class CustomerMinimumCreditCardsException extends Exception {
        CustomerMinimumCreditCardsException() {
            super("The last credit card cannot be removed.");
        }
    }
    
    /**
     * An exception for when an attempt at trying to remove all {@link CreditCard}s from the {@link CreditCardList} ends up failing.
     */
    class CustomerCouldNotDeleteCreditCardsException extends Exception {
    	CustomerCouldNotDeleteCreditCardsException() {
            super("The customer's credit card(s) could not be deleted.");
        }
    }
    
    /**
     * An exception for when trying to add a {@link CreditCard} to a {@link Customer}'s account when the {@link CreditCard} is already in use.
     */
    class CustomerDuplicateCardException extends Exception {
    	CustomerDuplicateCardException() {
            super("The credit card entered is already in use.");
        }
    }

    
}