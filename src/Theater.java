import java.io.*;
import java.util.Date;
import java.util.Iterator;

/**
 * A façade (and singleton) for the Theater.
 * This should be the main entry point for all interface actions; in a sense, it is the API layer.
 * Much of the overall functionality is actually implemented through getClientList, getCustomer, and getShowList, however.
 *
 * Note a couple of design decisions:
 ** The various singleton hashmaps are never interacted with directly; instead, their instances are always returned through the Theater façade. This is done to lower coupling.
 ** Classes that duplicate functionality have that functionality implemented in generic superclasses as much as possible. This is done to maximise cohesion and reduce bugs (if one class has a bug, the others will as well, making detection easier).
 ** All operations that perform validation in Theater, ClientList, CustomerList, and ShowList will throw checked exceptions for the validation instead of integer status codes. A façade could copy most UserInterface functionality to specifically return the enumerated values to UserInterface, and in Iteration 2 Theater will do so (I would have done so now, but the change to the sequence diagrams would have been too large.)
 *
 * Known Bugs:
 ** The singleton property for the different lists is not correctly maintained through Class.getInstance(). It does seem to be maintained through Theater.getClass(). This should be fixed for it. 2.
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
     * Returned codes used from {@link Theater#storeData()}.
     */
    enum STORE_DATA_STATUS {
        /**
         * Generic failure occurred. */
        FAILURE,
        /**
         * Method ended successfully. */
        SUCCESS,
    }

    /**
     * Writes Theater's state to the persistence file.
     *
     * @return True on success, false on failure.
     */
    public static STORE_DATA_STATUS storeData() {
        try {
            FileOutputStream out = new FileOutputStream(persistenceFile);
            ObjectOutputStream oos = new ObjectOutputStream(out);

            oos.writeObject(INSTANCE);
            oos.flush();
            return STORE_DATA_STATUS.SUCCESS;
        } catch (Exception e) {
            System.err.println("Theater.storeData: Unable to serialise a value: " + e);
            return STORE_DATA_STATUS.FAILURE;
        }
    }


    /**
     * Returned codes used from {@link Theater#retrieveData()}.
     */
    enum RETRIEVE_DATA_STATUS {
        /**
         * Generic failure occurred. */
        FAILURE,
        /**
         * Method ended successfully. */
        SUCCESS,
        /**
         * Data was previously loaded this session. */
        ALREADY_LOADED,
    }

    /**
     * Loads in data from the persistence file.
     * 
     * @return The Theater instance on success, or null on failure.
     */
    public static RETRIEVE_DATA_STATUS retrieveData() {
        if (dataRetrieved) {
            return RETRIEVE_DATA_STATUS.ALREADY_LOADED;
        }
        else {
            try {
                FileInputStream in = new FileInputStream(persistenceFile);
                ObjectInputStream ois = new ObjectInputStream(in);
                ois.readObject();
                return RETRIEVE_DATA_STATUS.SUCCESS;
            } catch (IOException e) {
                System.err.println("Theater.retrieveData: Problem reading: " + e);
                return RETRIEVE_DATA_STATUS.FAILURE;
            } catch (ClassNotFoundException e) {
                System.err.println("Theater.retrieveData: Class not found: " + e);
                return RETRIEVE_DATA_STATUS.FAILURE;
            }
        }
    }



    /*################################
     * API Methods
     * These interact with objects, while UserInterface performs the IO and invokes these functions.
     *###############################*/

    /**
     * Returned codes used from {@link Theater#addClient(String, String, long)}.
     */
    enum ADD_CLIENT_STATUS {
        /**
         * Generic failure occurred. */
        FAILURE,
        /**
         * Method ended successfully. */
        SUCCESS,
        /**
         * The phone number used is out of the valid range of phone numbers. */
        PHONE_NUMBER_OUT_OF_RANGE
    }

    public static ADD_CLIENT_STATUS addClient(String name, String address, long phone) {
        try {
            // Add New Account Object to Client List
            Client client = new Client(name, address, phone);

            if (getClientList().addAccount(client)) {
                return ADD_CLIENT_STATUS.SUCCESS;
            }
            else {
                return ADD_CLIENT_STATUS.FAILURE;
            }
        } catch (AccountPhoneNumberOutOfRangeException ex) {
            return ADD_CLIENT_STATUS.PHONE_NUMBER_OUT_OF_RANGE;
        }
    }


    /**
     * Returned codes used from {@link Theater#removeClient(int)}.
     */
    enum REMOVE_CLIENT_STATUS {
        /**
         * The client to be removed does not exist. */
        NOEXIST,
        /**
         * Generic failure occurred. */
        FAILURE,
        /**
         * Method ended successfully. */
        SUCCESS,
        /**
         * The method failed because the client to be removed has an ongoing show. */
        ONGOING_SHOW
    }

    public static REMOVE_CLIENT_STATUS removeClient(int clientId) {
        Client client = getClientList().getAccount(clientId);

        if (client == null) {
            return REMOVE_CLIENT_STATUS.NOEXIST;
        }
        else {
            try {
                if (getClientList().removeClient(client.getId())) {
                    return REMOVE_CLIENT_STATUS.SUCCESS;
                }
                else {
                    return REMOVE_CLIENT_STATUS.FAILURE;
                }
            } catch (ClientListOngoingShowsException ex) {
                return REMOVE_CLIENT_STATUS.ONGOING_SHOW;
            }
        }
    }



    public static Iterator<Client> getClients() {
        return getClientList().iterator();
    }





    public final static int SELL_TICKET_FAILURE = 0;
    public final static int SELL_TICKET_SUCCESS = 1;

    public static int sellTickets(TicketType ticketType, int quantity, int customerId, long creditcardNumber, Date showDate) {
        Ticket t = ticketType.getNewTicket(getShowList().getEntry(showDate));
        System.out.println("Price of ticket:" + t.getPrice());

        return SELL_TICKET_FAILURE;
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