import java.io.Serializable;

/**
 * A Theater Client's Account.
 *
 * Created by Joseph T. Parsons on 12/06/17.
 */
public class Client extends Account implements Serializable {
	private int balance = 0;
	
	/**
	    * Constructor for the client class.
	    * 
	    * @param name the name of the client account holder
	    * @param address the address of the client account holder
	    * @param phoneNumber the phone number of the client account holder
	    * 
	    * @return nothing
	    */
    public Client(String name, String address, long phoneNumber) {
        super(name, address, phoneNumber);
    }
    
    /**
     * Gets the current balance of the client
     * 
     * @param nothing
     * 
     * @return an integer representing the balance of the client
     */
    public int getBalance() {
        return balance;
    }

    /**
     * Adjusts the balance of the client
     * 
     * @param balanceAdjustment the amount the balance is to be incremented by
     * 
     * @return an integer representing the adjusted balance
     */
    public int adjustBalance(int balanceAdjustment) {
        return this.balance += balanceAdjustment;
    }
    
    /**
     * Overrides the toString method of Account
     * 
     * @param nothing
     * 
     * @return a string representation concatenating basic client account information
     */
    @Override
    public String toString() { 
       return super.toString() + ", " + getBalance();
    }
}