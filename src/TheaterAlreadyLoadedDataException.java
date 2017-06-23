/**
 * Created by joseph on 22/06/17.
 */
public class TheaterAlreadyLoadedDataException extends Exception {
    public TheaterAlreadyLoadedDataException() {
        super("The theater has already loaded data once this session.");
    }
}
