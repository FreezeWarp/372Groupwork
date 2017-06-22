import java.io.Serializable;

/**
 * A basic integer implementation of the Identifiable interface.
 *
 * @author Joseph T. Parsons
 */
public class IdentifiableInteger implements Identifiable<Integer>, Serializable {
    Integer id;

    /**
     * Gets the Id 
     * 
     * @return the Id to identify the account object
     */
    public Integer getId() {
        return id;
    }

    /**
     * Sets the Id
     * 
     * @param id The new Id for the account
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Increments the Id by 1
     * 
     * @param id The Id that is to be incremented
     * 
     * @return the Id incremented by 1
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