import java.io.Serializable;

/**
 * A basic integer implementation of the Identifiable interface.
 *
 * @author  Joseph T. Parsons
 * @version 1.0
 * @since   2017-06-22
 */
public class IdentifiableInteger implements Identifiable<Integer>, Serializable {
    /**
     * The unique ID of the object.
     */
    Integer id;


    /**
     * @return the unique ID of the object, {@link IdentifiableInteger#id}
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id The new id for the object, {@link IdentifiableInteger#id}
     */
    public void setId(Integer id) {
        this.id = id;
    }


    /**
     * Returns a new, unique ID based on the previously issued ID by incrementing it by 1.
     * 
     * @param id The ID most-recently issued by this method.
     *
     * @return The new, unique ID.
     */
    public Integer nextId(Integer id) {
        if (id == null) {
            return 0;
        }
        else {
            return id + 1;
        }
    }
}