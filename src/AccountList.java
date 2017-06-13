/**
 * A list of Accounts.
 *
 * Created by joseph on 12/06/17.
 */

import java.io.IOException;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by joseph on 12/06/17.
 */
public class AccountList<E extends Account> implements Iterable<E>, Serializable {
    /* Singleton Stuff */
    protected static AccountList INSTANCE;

    protected AccountList() { }

    public static AccountList getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new AccountList();
        }

        return INSTANCE;
    }



    /* Singleton Serialisation Stuff */
    /**
     * Reads the Theater object (and its static instance variable) from the ObjectOutputStream.
     *
     * @param input
     */
    private void readObject(java.io.ObjectInputStream input) {
        try {
            input.defaultReadObject();

            if (INSTANCE == null) {
                INSTANCE = (AccountList) input.readObject();
            }
            else {
                input.readObject();
            }
        } catch(IOException ioe) {
            ioe.printStackTrace();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * Writes the Theater object (and its static instance variable) to the ObjectOutputStream.
     *
     * @param output
     */
    private void writeObject(java.io.ObjectOutputStream output) {
        try {
            output.defaultWriteObject();
            output.writeObject(INSTANCE);
        } catch(IOException ioe) {
            ioe.printStackTrace();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }



    /* The List Itself */
    /**
     * A map of accounts. Keyed by a unique account ID.
     */
    private Map<Integer, E> AccountList = new HashMap<Integer, E>();

    /**
     * The last account ID that was assigned. Use {@link AccountList#getNewAccountId()} to get the next account ID in the series.
     */
    private int lastAccountId = 0;


    /**
     * An iterator that iterates through the list of Accounts.
     */
    public Iterator<E> iterator() {
        return AccountList.values().iterator();
    }

    /**
     * Adds a new account to the AccountList.
     *
     * @param account
     */
    public void addAccount(E account) {
        AccountList.put(account.getId(), account);
    }

    /**
     * Removes an account from the AccountList, by its ID.
     *
     * @param accountId The accountID belonging to the account to remove.
     *
     * @return True on success, false on failure (ID doesn't exist, probably)
     */
    public boolean removeAccount(int accountId) {
        if (AccountList.containsKey(accountId)) {
            AccountList.remove(accountId);
            return true;
        }
        else {
            return false;
        }
    }

    /**
     * @return A new, unique account ID.
     */
    public int getNewAccountId() {
        return lastAccountId++;
    }
}
