/**
 * An exception for when trying to remove a {@link Client} from {@link ClientList} when the client has future {@link Show}s and thus cannot be removed.
 *
 * @author  Joseph Parsons
 * @version 1.0
 * @since   2017-06-22
 */
public class ClientListOngoingShowsException extends Exception {
    ClientListOngoingShowsException () {
        super("The client cannot be removed if it has shows ongoing.");
    }
}