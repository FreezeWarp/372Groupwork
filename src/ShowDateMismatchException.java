/**
 * An exception for when trying to create a {@link Show} with an end date that is in the future of the start date.
 *
 * @author  Joseph Parsons
 * @version 1.0
 * @since   2017-06-22
 */
public class ShowDateMismatchException extends Exception {
    ShowDateMismatchException() {
        super("The end date of a show cannot be before its start date.");
    }
}