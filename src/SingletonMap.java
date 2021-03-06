import java.io.IOException;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.Iterator;
import java.util.NavigableMap;
import java.util.TreeMap;

/**
 * This defines a basic hashmap that should be treated as a Singleton.
 * While this could almost be abstract, we want to be able to initialise it for the serilisation methods readObject/writeObject.
 *
 * @author  Joseph T. Parsons
 * @version 2.0
 * @since   2017-July-08
 */
public class SingletonMap<K, E> implements Iterable<E>, Serializable {
    /*################################
     * Singleton Serialisation
     *###############################*/

    /**
     * Reads the Theater object (and its static instance variable) from the ObjectOutputStream.
     *
     * @param input The stream being read from.
     */
    private void readObject(java.io.ObjectInputStream input) {
        try {
            Field field = this.getClass().getDeclaredField("INSTANCE");
            SingletonMap<K, E> dummy = new SingletonMap();
            Object instance = field.get(dummy);
            try {
                input.defaultReadObject();

                if (instance == null) {
                    field.set(dummy, input.readObject());
                } else {
                    input.readObject();
                }
            } catch (IOException ioe) {
                ioe.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (NoSuchFieldException e) {
            System.err.println("INSTANCE variable unavailable in implementation of SingletonMap. Reading object failed.");
        } catch (IllegalAccessException e) {
            System.err.println("Unable to read INSTANCE method. Reading object failed.");
        }
    }


    /**
     * Writes the Theater object (and its static instance variable) to the ObjectOutputStream.
     *
     * @param output The stream being written to.
     */
    private void writeObject(java.io.ObjectOutputStream output) {
        try {
            Field field = this.getClass().getDeclaredField("INSTANCE");
            SingletonMap<K, E> dummy = new SingletonMap();

            output.defaultWriteObject();
            output.writeObject(field.get(dummy));
        } catch(IOException ioe) {
            ioe.printStackTrace();
        } catch (NoSuchFieldException e) {
            System.err.println("INSTANCE variable unavailable in implementation of SingletonMap. Writing object failed.");
        } catch (IllegalAccessException e) {
            System.err.println("Unable to read INSTANCE method. Writing object failed.");
        }
    }



    /*################################
     * The Core List Functionality
     *###############################*/

    /**
     * A Map of E-typed objects, keyed by an integer that should be entirely unique.
     */
    protected NavigableMap<K, E> singletonMap = new TreeMap<K, E>();


    /**
     * An iterator that iterates through the list of Map entries (values).
     * 
     * @return the next value in the singletonMap.
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
     * @return True on success, false on failure. (ID doesn't exist, probably)
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
     * Overrides the toString method of Account.
     * 
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
