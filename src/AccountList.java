/**
 * Created by joseph on 12/06/17.
 */
import java.io.IOException;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Iterator;

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
    private HashMap<Integer, E> AccountList = new HashMap<Integer, E>();
    private int lastAccountId = 0;

    public Iterator<E> iterator() {
        return AccountList.values().iterator();
    }

    public void addAccount(E account) {
        AccountList.put(account.getId(), account);
    }

    public boolean removeAccount(int accountId) {
        if (AccountList.containsKey(accountId)) {
            AccountList.remove(accountId);
            return true;
        }
        else {
            return false;
        }
    }

    public int getNewAccountId() {
        return lastAccountId++;
    }
}
