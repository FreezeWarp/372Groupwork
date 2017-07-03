import java.util.ArrayList;

/**
 * A Theater Customer's Account.
 *
 * Created by Joseph T. Parsons on 12/06/17.
 * Modified by Eric Fulwiler
 */
public class Customer extends Account {
    private ArrayList<CreditCard> creditCardList = new ArrayList<CreditCard>();
    
    /**
     * @param name the name of the customer
     * @param address the address of the customer
     * @param phoneNumber the phone number of the customer
     * @param creditCard the first credit card for the customer
     */
    public Customer(String name, String address, long phoneNumber, CreditCard creditCard) throws AccountPhoneNumberOutOfRangeException, CreditCardListDuplicateCardException {
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
    public boolean addCreditCard(CreditCard creditCard) throws CreditCardListDuplicateCardException {
    	if (CreditCardList.getInstance().hasEntry(creditCard)) {
    		throw new CreditCardListDuplicateCardException();
    	}
    	
        Theater.getCreditCardList().addEntry(creditCard, this); //NEW IMPLEMENTATION
        return creditCardList.add(creditCard);
     
    }
    
    
    /**
     * Returns the default credit card of the customer
     * 
     * @return the starting credit card object
     */
    public CreditCard getCreditCard() {
        return creditCardList.get(0);
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
                CreditCardList.getInstance().removeEntry(creditCard);
            }
        }

        return creditCardList.removeIf((CreditCard creditCard) -> creditCard.getCardNumber() == creditCardNumber);
    }
    
    /**
     * Removes all credit cards from the customer's account. To be used when a customer wishes to have their entire account deleted.
     */
    public boolean removeCreditCards() throws CustomerCouldNotDeleteCreditCardsException {
        creditCardList.clear();
        if (creditCardList.isEmpty())  {
        	return true;
        }
        else {
        	throw new CustomerCouldNotDeleteCreditCardsException();
        }
    }
       
    /**
     * Gets the entire credit card ArrayList
     * 
     * @return the ArrayList containing credit card objects 
     */
    public ArrayList<CreditCard> getCreditCardList() {
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
        
        for (CreditCard creditCard : creditCardList) {
        	creditCardString += creditCard;
        }
        
        return super.toString() + creditCardString;
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
     * An exception for when trying to remove a {@link CreditCard} from a {@link Customer} when the customer only has one associated {@link CreditCard}.
     */
    class CreditCardListDuplicateCardException extends Exception {
    	CreditCardListDuplicateCardException() {
            super("This credit card already exists in the database.");
        }
    }

    
}