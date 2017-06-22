/**
 * Created by joseph on 22/06/17.
 */
public class ShowConflictException extends Exception {
    ShowConflictException() {
        super("The show conflicts with an existing show.");
    }
}