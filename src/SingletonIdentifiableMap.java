import java.io.Serializable;

/**
 * Created by joseph on 29/06/17.
 */
public abstract class SingletonIdentifiableMap<K, E extends Identifiable<K>> extends SingletonMap<K, E> implements Iterable<E>, Serializable {
    /*################################
     * The Core List Functionality
     *###############################*/
    /**
     * The last Map key that was assigned. Use {@link SingletonIdentifiableMap#getNewKey(E)} to get the next key in the series.
     */
    private K lastKey;


    /**
     * Gets a new unique map key
     *
     * @param entry The entry object that we are getting a map key for
     *
     * @return A new, unique Map key.
     */
    public K getNewKey(E entry) {
        return lastKey = entry.nextId(lastKey);
    }


    public boolean addEntry(E entry) {
        entry.setId(getNewKey(entry)); // Set the ID to a newly-created ID used for uniquely identifying objects in the hashmap.

        return super.addEntry(entry.getId(), entry);
    }


}
