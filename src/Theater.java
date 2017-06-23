import java.io.*;

/**
 * A façade (and singleton) for the Theater.
 * This should be the main entry point for all interface actions; in a sense, it is the API layer.
 * Much of the overall functionality is actually implemented through getClientList, getCustomer, and getShowList, however.
 *
 * Note a couple of design decisions:
 ** The various singleton hashmaps are never interacted with directly; instead, their instances are always returned through the Theater façade. This is done to lower coupling.
 ** Classes that duplicate functionality have that functionality implemented in generic superclasses as much as possible. This is done to maximise cohesion and reduce bugs (if one class has a bug, the others will as well, making detection easier).
 ** All operations that perform validation in Theater, ClientList, CustomerList, and ShowList will throw checked exceptions for the validation instead of integer status codes. A façade could copy most UserInterface functionality to specifically return the sentinel values to UserInterface, but that seemed a bit excessive for now. (UserInterface also performs its own basic validation in some cases; the validation performed by the singletons should be authoritative, however.) Note that I would aruge this is a workable paradigm in API design: APIs can return their own "exceptions" with detailed data to be parsed by the client, while before sending data to the server, the client should be performing some of its own validation as well.
 *
 * @author  Eric Fulwiler, Daniel Johnson, Joseph Parsons, and Cory Stadther
 * @version 1.0
 * @since   2017-06-22
 */
public class Theater implements Serializable {
    /*################################
     * Singleton-Specific Functionality
     *###############################*/

    /**
     * The global singleton instance of Theater. It can be initialised by {@link Theater#getInstance()}, if needed.
     */
    private static Theater INSTANCE;


    /**
     * An unused constructor that overrides the default public constructor, preventing Theater from being initialised outside of getInstance().
     */
    private Theater() {}


    /**
     * @return The singleton instance of Theater. It will be initialised, if necessary.
     */
    public static Theater getInstance() {
        if (INSTANCE == null) {
             INSTANCE = new Theater();
        }

        return INSTANCE;
    }



    /*################################
     * Singleton Serialisation
     *###############################*/

    /**
     * Reads the Theater object (and its static instance variable) from the ObjectOutputStream.
     *
     * @param input The stream being read from.
     */
    private void readObject(java.io.ObjectInputStream input) {
        try {
            input.defaultReadObject();

            if (INSTANCE == null) {
                INSTANCE = (Theater) input.readObject();
            }
            else {
                input.readObject();
            }
        } catch(IOException ioe) {
            ioe.printStackTrace();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * Writes the Theater object (and its static instance variable) to the ObjectOutputStream.
     *
     * @param output The stream being written to.
     */
    private void writeObject(java.io.ObjectOutputStream output) {
        try {
            output.defaultWriteObject();
            output.writeObject(INSTANCE);
        } catch(IOException ioe) {
            ioe.printStackTrace();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }



    /*################################
     * Helper Methods to Write/Read to/from Disk
     *###############################*/

    /**
     * A file object corresponding to the persistence file, whether or whether not it exists.
     */
    private static final File persistenceFile = new File(new File(System.getProperty("user.dir")), "372Groupwork_Persistence.bin");


    /**
     * Whether or not data has already been loaded for this session.
     * Once data is loaded, it should not be possible to load it again.
     */
    static boolean dataRetrieved = false;


    /**
     * Determines if the persistence file exists with data
     * 
     * @return True if the persistence file exists, false otherwise.
     */
    public static boolean hasData() {
        return persistenceFile.exists();
    }


    /**
     * Writes Theater's state to the persistence file.
     *
     * @return True on success, false on failure.
     */
    public static boolean storeData() {
        try {
            FileOutputStream out = new FileOutputStream(persistenceFile);
            ObjectOutputStream oos = new ObjectOutputStream(out);

            oos.writeObject(INSTANCE);
            return true;
            //oos.flush();
        } catch (Exception e) {
            System.err.println("Theater.storeData: Unable to serialise a value: " + e);
            return false;
        }
    }


    /**
     * Loads in data from the persistence file.
     * 
     * @return The Theater instance on success, or null on failure.
     */
    public static Theater retrieveData() throws TheaterAlreadyLoadedDataException {
        if (dataRetrieved) {
            throw new TheaterAlreadyLoadedDataException();
        }
        else {
            try {
                FileInputStream in = new FileInputStream(persistenceFile);
                ObjectInputStream ois = new ObjectInputStream(in);
                ois.readObject();
                return INSTANCE;
            } catch (IOException e) {
                System.err.println("Theater.retrieveData: Problem reading: " + e);
                return null;
            } catch (ClassNotFoundException e) {
                System.err.println("Theater.retrieveData: Class not found: " + e);
                return null;
            }
        }
    }



    /*################################
     * Client/Customer/Show Instances
     *###############################*/

    /**
     * The global singleton instance of {@link ClientList}.
     */
    private ClientList clientList = ClientList.getInstance();

    /**
     * The global singleton instance of {@link CustomerList}.
     */
    private CustomerList customerList = CustomerList.getInstance();

    /**
     * The global singleton instance of {@link ShowList}.
     */
    private ShowList showList = ShowList.getInstance();


    /**
     * @return The singleton instance of {@link ClientList}, allowing modification of the client list singleton or use of ClientList-specific functionality.
     */
    public static ClientList getClientList() {
        return getInstance().clientList;
    }

    /**
     * @return The singleton instance of {@link CustomerList}, allowing modification of the show list or use of CustomerList-specific functionality.
     */
    public static CustomerList getCustomerList() {
        return getInstance().customerList;
    }

    /**
     * @return The singleton instance of {@link ShowList}, allowing modification of the show list or use of ShowList-specific functionality.
     */
    public static ShowList getShowList() {
        return getInstance().showList;
    }


}