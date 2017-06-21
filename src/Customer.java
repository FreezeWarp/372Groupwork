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
     * Constructor for the Customer class 
     * 
     * @param name the name of the customer
     * @param address the address of the customer
     * @param phoneNumber the phone number of the customer
     * @param creditCard the first credit card for the customer
     *                  
     * @return nothing
     */
    public Customer(String name, String address, long phoneNumber, CreditCard creditCard) {
        super(name, address, phoneNumber);
        creditCardList.add(creditCard);
    }
    

    /**
     * Adds a credit card to the customer's account
     * 
     * @param creditCard the new credit card being added
     * 
     * @return nothing
     */
    public void addCreditCard(CreditCard creditCard) {
        creditCardList.add(creditCard);
    }
    
    
    /**
     * Returns the default credit card of the customer
     * 
     * @param nothing
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
     * @return nothing
     */
    public void removeCreditCard(long creditCardNumber) {
        creditCardList.removeIf((CreditCard creditCard) -> creditCard.getCardNumber() == creditCardNumber);
    }
    
    /**
     * Removes all credit cards from the customer's account
     * 
     * @param nothing
     * 
     * @return nothing 
     */
    public void removeCreditCards() {
        creditCardList.clear();
    }
       
    /**
     * Gets the entire credit card ArrayList
     * 
     * @param nothing
     * 
     * @return the ArrayList containing credit card objects 
     */
    public ArrayList<CreditCard> getCreditCardList() {
        return creditCardList;
    }
     
    /**
     * Overrides the toString method of Account
     * 
     * @param nothing
     * 
     * @return a string representation concatenating basic customer information
     */
    @Override
    public String toString() {
        String creditCardString = "";
        
        for (int index = 0; index < creditCardList.size(); index++) {
            creditCardString += index + ": " + creditCardList.get(index);
        }
        
        return super.toString() + creditCardString;
    } 
}