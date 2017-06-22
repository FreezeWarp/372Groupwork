/**
 * Created by joseph on 22/06/17.
 */
public class ShowDateMismatchException extends Exception {
    ShowDateMismatchException() {
        super("The end date of a show cannot be before its start date.");
    }
}