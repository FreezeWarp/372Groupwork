/**
 * A basic integer implementation of the Identifiable interface.
 *
 * @author Joseph T. Parsons
 */

public class IdentifiableInteger implements Identifiable<Integer> {
    Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer nextId(Integer id) {
        if (id == null) {
            return 0;
        }
        else {
            return id + 1;
        }
    }
}