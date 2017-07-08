import java.io.Serializable;

/**
 * This defines a SingletonMap that _automatically_, using the fields implemented by the Identifiable interface, assigns key values to an object and to the hashmap entries themselves.
 * In a way, this essentially mimics an autoincrementing database table, and could even be implemented using those in the future.
 * Some justification for using the hashmap approach over a list with an equals search:
 ** It is much faster (searches perform O(1) hash lookups instead of linear O(n) searches). In my experience, this is generally a compelling reason on its own terms, especially since searches are used for all operations: remove, add, and has.
 ** The increase in coupling is minor; we only require that an object stored with this implement the Identifiable interface correctly (if the interface is implemented incorrectly, we will typically detect this and return responses accordingly). This is three simple methods.
 ** Plus, by having these methods, a lot of extra work can be simplified; we automatically assign new IDs to the objects, for instance.
 *
 * @author  Joseph T. Parsons
 * @version 2.0
 * @since   2017-July-08
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


    /**
     * Adds an entry to the singleton map. It's key will be from entry.getId(), which will (typically) be first set using entry.setId(unqiueID).
     *
     * @param entry The entry to add.
     *
     * @return True on success, false on failure.
     */
    public boolean addEntry(E entry) {
        entry.setId(getNewKey(entry)); // Set the ID to a newly-created ID used for uniquely identifying objects in the hashmap.

        return super.addEntry(entry.getId(), entry);
    }
}
