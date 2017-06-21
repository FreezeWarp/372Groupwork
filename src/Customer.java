import java.util.ArrayList;

/**
 * A Theater Customer's Account.
 *
 * Created by joseph on 12/06/17.
 * @modified by Eric Fulwiler
 */
public class Customer extends Account {
    private ArrayList<CreditCard> creditCardList = new ArrayList<CreditCard>();

    /**
     * Constructor 
     * 
     * This constructor is called the first time a user is created. 
     * 
     * @param name
     * 					the name of the customer
     * @param address
     * 					the address of the customer
     * @param phoneNumber
     *                  the phone number of the customer
     * @param creditCard 
     *                  the first credit card for the customer
     */
    public Customer(String name, String address, long phoneNumber, CreditCard creditCard) {
        super(name, address, phoneNumber);
        creditCardList.add(creditCard);
    }
    

    /**
     * Adds a credit card to the customer's account
     * 
     * @param creditCard
     * 					the new credit card being added
     */
    public void addCreditCard(CreditCard creditCard) {
    	creditCardList.add(creditCard);
    }
    

    /**
     * Returns the first credit card of the customer
     * 
     * @return creditCard object
     * 					the starting credit card
     */
    public CreditCard getCreditCard() {
    	return creditCardList.get(0);
    }
    
    /**
     * Removes the specified credit card from the customer's account
     * 
     * @param creditCardNumber
     * 					the number of the credit card to be removed
     */
    public void removeCreditCard(long creditCardNumber) {
    	 creditCardList.removeIf((CreditCard creditCard) -> creditCard.getCardNumber() == creditCardNumber);
   
    }
    
    /**
     * Removes all credit cards from the customer's account
     * 
     */
    public void removeCreditCards() {
    	creditCardList.clear();
    }
    
    /**
     * Returns the credit card object specified
     * 
     * @param index
     * 					the index of the credit card to be retrieved
     */
    public CreditCard getCreditCard(int index) {
    	return creditCardList.get(index);
    }
    
    /**
     * Returns the entire credit card ArrayList
     * 
     * @return ArrayList<CreditCard>
     * 					the ArrayList containing credit card objects 
     */
    public ArrayList<CreditCard> getCreditCardList() {
    	return creditCardList;
    }
    
    
    /**
     * Returns a string representation of the customer information
     * 
     * @return ""
     * 					a string representation of the customer information
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