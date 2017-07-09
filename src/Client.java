import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

/**
 * The account of a theater client, one who actually owns the IP rights to shows being presented.
 *
 * @author  Joseph T. Parsons
 * @version 2.0
 * @since   2017-July-08
 */
public class Client extends Account implements Serializable {
    /**
     * The Client's current monetary balance.
     */
    private double balance = 0;

    /**
     * A list of shows the Client has. These are indexed in ShowList for most purposes, but having them indexed here as well is helpful.
     */
    private Collection<Show> showList = new ArrayList<Show>();


    /**
     * @param name the name of the client account holder, {@link Account#name}.
     * @param address the address of the client account holder, {@link Account#address}.
     * @param phoneNumber the phone number of the client account holder, {@link Account#phoneNumber}.
     */
    public Client(String name, String address, long phoneNumber) throws AccountPhoneNumberOutOfRangeException {
        super(name, address, phoneNumber);
    }


    /**
     * Adds a show to the index.
     *
     * @param show The show to add.
     * @return True on success, false on failure.
     */
    public boolean addShow(Show show) {
        return showList.add(show);
    }


    /**
     * Removes a show.
     *
     * @param show The show to remove.
     * @return True on success, false on failure.
     */
    public boolean removeShow(Show show) {
        return showList.remove(show);
    }


    /**
     * @return All of the client's shows.
     */
    public Collection<Show> getShows() {
        return showList;
    }


    /**
     * @return The balance of the client, {@link Client#balance}.
     */
    public double getBalance() {
        return balance;
    }


    /**
     * Adjusts the balance of the client.
     *
     * @param balanceAdjustment the amount the balance is to be incremented by.
     *
     * @return An integer reflecting the new, adjusted balance.
     */
    public double adjustBalance(double balanceAdjustment) {
        return this.balance += balanceAdjustment;
    }



    /**
     * Overrides the toString method of Account.
     *
     * @return A string representation concatenating basic client account information.
     */
    @Override
    public String toString() {
        String showListString = "";

        for (Show show : showList) {
            showListString += ("      " + show.toString() + System.getProperty("line.separator"));
        }

        return super.toString() + ", Owed $" + getBalance() + ", Shows:" + System.getProperty("line.separator") +
            showListString; // A bit of a silly one-liner, I'll admit, but it gets the job done.
    }
}