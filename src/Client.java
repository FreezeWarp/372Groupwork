import java.io.Serializable;

/**
 * The account of a theater client, one who actually owns the IP rights to shows being presented.
 *
 * @author  Joseph T. Parsons
 * @version 1.0
 * @since   2017-06-22
 */
public class Client extends Account implements Serializable {
    /**
     * The Client's current monetary balance.
     */
    private int balance = 0;


    /**
     * @param name the name of the client account holder, {@link Account#name}
     * @param address the address of the client account holder, {@link Account#address}
     * @param phoneNumber the phone number of the client account holder, {@link Account#phoneNumber}
     */
    public Client(String name, String address, long phoneNumber) throws AccountPhoneNumberOutOfRangeException {
        super(name, address, phoneNumber);
    }


    /**
     * @return the balance of the client, {@link Client#balance}
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