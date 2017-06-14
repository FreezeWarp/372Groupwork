import java.io.IOException;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by joseph on 12/06/17.
 */
public class SingletonHashmap<E> implements Iterable<E>, Serializable {
    /* Singleton Stuff */
    private static SingletonHashmap INSTANCE;

    protected SingletonHashmap() { }

    public static SingletonHashmap getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new SingletonHashmap();
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
                INSTANCE = (SingletonHashmap) input.readObject();
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
    protected Map<Integer, E> singletonHashmap = new HashMap<Integer, E>();

    /**
     * The last account ID that was assigned. Use {@link AccountList#getNewAccountId()} to get the next account ID in the series.
     */
    private int lastId = 0;


    /**
     * An iterator that iterates through the list of Accounts.
     */
    public Iterator<E> iterator() {
        return singletonHashmap.values().iterator();
    }

    /**
     * @return A new, unique account ID.
     */
    public int getNewId() {
        return lastId++;
    }

    /**
     * Adds a new account to the AccountList.
     *
     * @param account
     */
    public void addEntry(int keyId, E entry) {
        singletonHashmap.put(keyId, entry);
    }

    /**
     * Removes an account from the AccountList, by its ID.
     *
     * @param accountId The accountID belonging to the account to remove.
     *
     * @return True on success, false on failure (ID doesn't exist, probably)
     */
    public boolean removeEntry(int keyId) {
        if (singletonHashmap.containsKey(keyId)) {
            singletonHashmap.remove(keyId);
            return true;
        }
        else {
            return false;
        }
    }
    
    /**
     * Retries an account from the AccountList, by its ID.
     *
     * @param accountId The accountID belonging to the account to be returned
     *
     * @return True on success, false on failure (ID doesn't exist, probably)
     */
    public Account getAccount(int accountID) {
         return (Account) singletonHashmap.get(accountID);
    	
    }
}
