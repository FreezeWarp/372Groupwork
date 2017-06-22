import java.io.Serializable;

/**
 * The account of a theater client, one who actually owns the IP rights to shows being presented.
 *
 * @author  Joseph T. Parsons
 * @version 1.0
 * @since   2017-06-22
 */
public class Client extends Account implements Serializable {
    private int balance = 0;


    /**
     * @param name the name of the client account holder
     * @param address the address of the client account holder
     * @param phoneNumber the phone number of the client account holder
     */
    public Client(String name, String address, long phoneNumber) {
        super(name, address, phoneNumber);
    }


    /**
     * Gets the current balance of the client
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
     * @return an integer reflecting the new, adjusted balance
     */
    public int adjustBalance(int balanceAdjustment) {
        return this.balance += balanceAdjustment;
    }


    /**
     * Overrides the toString method of Account
     *
     * @return a string representation concatenating basic client account information
     */
    @Override
    public String toString() {
        return super.toString() + ", " + getBalance();
    }
}