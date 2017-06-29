import java.io.IOException;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * This defines two things: a hashmap with entries identified by an ID; and that should be treated as a singleton, with persistence methods overridden accordingly.
 * In a way, this essentially mimics a database table, and could even be implemented using those in the future.
 * Some justification for using the hashmap approach over a list with an equals search:
 ** It is much faster (searches perform O(1) hash lookups instead of linear O(n) searches). In my experience, this is generally a compelling reason on its own terms, especially since searches are used for all operations: remove, add, and has.
 ** The increase in coupling is minor; we only require that an object stored with this implement the Identifiable interface correctly (if the interface is implemented incorrectly, we will typically detect this and return responses accordingly). This is three simple methods.
 ** Plus, by having these methods, a lot of extra work can be simplified; we automatically assign new IDs to the objects, for instance.
 *
 * @author  Joseph T. Parsons
 * @version 1.0
 * @since   2017-06-22
 */
public abstract class SingletonMap<K, E> implements Iterable<E>, Serializable {
    /*################################
     * Singleton-Specific Functionality
     *###############################*/
    /**
     * The global singleton instance of SingletonMap. This will be overwritten by all non-abstract extensions of SingletonMap, but notably is required for readObject/writeObject functionality.
     */
    private static SingletonMap INSTANCE;



    /*################################
     * Singleton Serialisation
     *###############################*/

    /**
     * Reads the Theater object (and its static instance variable) from the ObjectOutputStream.
     *
     * @param input The stream being read from
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
     * @param output The stream being written to
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



    /*################################
     * The Core List Functionality
     *###############################*/

    /**
     * A Map of E-typed objects, keyed by an integer that should be entirely unique.
     */
    protected Map<K, E> singletonMap = new HashMap<K, E>();


    /**
     * An iterator that iterates through the list of Map entries (values).
     * 
     * @return the next value in the singletonMap
     */
    public Iterator<E> iterator() {
        return singletonMap.values().iterator();
    }


    /**
     * Adds a new Map entry to the Map.
     *
     * @param entry The entry object to add.
     *
     * @return True on success, false on failure (typically, an entry with the same key already exists).
     */
    public boolean addEntry(K value, E entry) {
        if (hasEntry(value)) { // Basically, detect a failure to set the ID correctly. While unlikely, this could happen if Identifiable is incorrectly implemented.
            return false;
        }
        else {
            singletonMap.put(value, entry);
            return true;
        }
    }


    /**
     * Removes a Map entry by its key.
     *
     * @param key The hashmap key to remove.
     *
     * @return True on success, false on failure (ID doesn't exist, probably)
     */
    public boolean removeEntry(K key) {
        if (hasEntry(key)) {
            singletonMap.remove(key);
            return true;
        }
        else {
            return false;
        }
    }


    /**
     * Retrieves a Map entry by its key.
     *
     * @param key The hashmap key whose value should be returned.
     *
     * @return A map entry, or null if none found.
     */
    public E getEntry(K key) {
        return singletonMap.get(key);
    }


    /**
     * Checks whether an entry with the given key exists.
     *
     * @param key The map key to check for.
     *
     * @return True if an entry exists, false otherwise.
     */
    public boolean hasEntry(K key) {
        return singletonMap.containsKey(key);
    }


    /**
     * Overrides the toString method of Account
     * 
     * @return A string representation of the Map, with each entry on its own line
     */
    public String toString() {
        StringBuilder sb = new StringBuilder();

        for (E entry : this) {
            sb.append(entry.toString() + "\n");
        }

        return sb.toString();
    }
}
