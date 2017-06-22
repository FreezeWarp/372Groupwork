/**
 * An exception for when trying to add a {@link Show} to {@link ShowList} when a conflicting Show already exists in the ShowList.
 *
 * @author  Joseph Parsons
 * @version 1.0
 * @since   2017-06-22
 */
public class ShowConflictException extends Exception {
    ShowConflictException() {
        super("The show conflicts with an existing show.");
    }
}