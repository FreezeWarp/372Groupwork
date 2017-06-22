/**
 * An interface for a specific set of methods that allow for an object to be "idenitifiable" -- that is, where 
 * we can both assign and retrieve the object's ID value, and where the object specifies a method for getting the 
 * next, unique method given knowledge of the last one that was issued.
 *
 * @author  Joseph T. Parsons
 * @version 1.0
 * @since   2017-06-22
 */
public interface Identifiable<K> {
    public void setId(K id);
    public K getId();
    public K nextId(K id);
}