import java.io.IOException;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * This defines two things: a hashmap with entries identified by an integer ID; and that should be treated as a singleton, with persistence methods overridden accordingly,
 * In a way, this essentially mimics a database table, and could even be implemented using those in the future.
 *
 * @author Joseph T. Parsons
 */
public class SingletonMap<E> implements Iterable<E>, Serializable {
    /* Singleton Stuff */
    /**
     * The singleton instance.
     */
    private static SingletonMap INSTANCE;

    protected SingletonMap() { }

    public static SingletonMap getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new SingletonMap();
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
                INSTANCE = (SingletonMap) input.readObject();
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
     * A Map of E-typed objects, keyed by an integer that should be entirely unique.
     */
    protected Map<Integer, E> singletonMap = new HashMap<Integer, E>();


    /**
     * The last Map key that was assigned. Use {@link SingletonMap#getNewKey()} to get the next key in the series.
     */
    private int lastKey = 0;


    /**
     * An iterator that iterates through the list of Map entries (values).
     */
    public Iterator<E> iterator() {
        return singletonMap.values().iterator();
    }


    /**
     * @return A new, unique Map key.
     */
    public int getNewKey() {
        return lastKey++;
    }


    /**
     * Adds a new Map entry to the Map.
     *
     * @param key The key to use for the entry.
     * @param entry The entry object itself.
     */
    public void addEntry(int key, E entry) {
        singletonMap.put(key, entry);
    }


    /**
     * Removes a Map entry by its key.
     *
     * @param key The hashmap key to remove.
     *
     * @return True on success, false on failure (ID doesn't exist, probably)
     */
    public boolean removeEntry(int key) {
        if (singletonMap.containsKey(key)) {
            singletonMap.remove(key);
            return true;
        }
        else {
            return false;
        }
    }


    /**
     * Checks whether an entry with the given key exists.
     *
     * @param key The hashmap key to check.
     *
     * @return True if an entry exists, false otherwise.
     */
    public boolean validateEntry(int key) {
        return singletonMap.containsKey(key);
    }


    /**
     * @return A string representation of the Map, with each entry on its own line.
     */
    public String toString() {
        StringBuilder sb = new StringBuilder();

        for (E entry : this) {
            sb.append(entry.toString() + "\n");
        }

        return sb.toString();
    }
}
