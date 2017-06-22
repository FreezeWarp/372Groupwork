/**
 * Created by joseph on 22/06/17.
 */
public class ClientListOngoingShowsException extends Exception {
    ClientListOngoingShowsException () {
        super("The client cannot be removed if it has shows ongoing.");
    }
}