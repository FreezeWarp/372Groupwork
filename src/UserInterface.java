import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * The interface used for all program interactions.
 * Note a couple of design decisions:
 ** The various singleton hashmaps are never interacted with directly; instead, their instances are always returned through the Theater façade. This is done to lower coupling.
 ** Classes that duplicate functionality have that functionality implemented in generic superclasses as much as possible. This is done to maximise cohesion and reduce bugs (if one class has a bug, the others will as well, making detection easier).
 */
public class UserInterface {
    /**
     * A Map of callable commands that are supported by the interface, keyed by the command used to invoke them.
     */
    static final Map<Integer, Runnable> commandMap = new HashMap<Integer, Runnable>();

    /**
     * A map of descriptions for {@link UserInterface#commandMap}.
     */
    static final Map<Integer, String> helpMap = new HashMap<Integer, String>();

    /*
     * A one-stop initialization of all values for both commandMap and helpMap.
     */
    static {
        final int COMMAND_EXIT = 0;
        helpMap.put(COMMAND_EXIT, "Exit the application.");
        commandMap.put(COMMAND_EXIT, () -> {}); // Do nothing -- this is handled specially inside of the command loop.

        final int COMMAND_ADD_CLIENT = 1;
        helpMap.put(COMMAND_ADD_CLIENT, "Add Client.");
        commandMap.put(COMMAND_ADD_CLIENT, () -> addClient());

        final int COMMAND_REMOVE_CLIENT = 2;
        helpMap.put(COMMAND_REMOVE_CLIENT, "Remove Client.");
        commandMap.put(COMMAND_REMOVE_CLIENT, () -> removeClient());

        final int COMMAND_LIST_CLIENTS = 3;
        helpMap.put(COMMAND_LIST_CLIENTS, "List all Clients.");
        commandMap.put(COMMAND_LIST_CLIENTS, () -> listClients());

        final int COMMAND_ADD_CUSTOMER = 4;
        helpMap.put(COMMAND_ADD_CUSTOMER, "Add Customer.");
        commandMap.put(COMMAND_ADD_CUSTOMER, () -> addCustomer());

        final int COMMAND_REMOVE_CUSTOMER = 5;
        helpMap.put(COMMAND_REMOVE_CUSTOMER, "Remove Customer.");
        commandMap.put(COMMAND_REMOVE_CUSTOMER, () -> removeCustomer());

        final int COMMAND_ADD_CREDITCARD = 6;
        helpMap.put(COMMAND_ADD_CREDITCARD, "Add a Credit Card.");
        commandMap.put(COMMAND_ADD_CREDITCARD, () -> addCreditCard());

        final int COMMAND_REMOVE_CREDITCARD = 7;
        helpMap.put(COMMAND_REMOVE_CREDITCARD, "Remove a Credit Card.");
        commandMap.put(COMMAND_REMOVE_CREDITCARD, () -> removeCreditCard());

        final int COMMAND_LIST_CUSTOMERS = 8;
        helpMap.put(COMMAND_LIST_CUSTOMERS, "List all Customers.");
        commandMap.put(COMMAND_LIST_CUSTOMERS, () -> listCustomers());

        final int COMMAND_ADD_SHOW = 9;
        helpMap.put(COMMAND_ADD_SHOW, "Add a Show/Play.");
        commandMap.put(COMMAND_ADD_SHOW, () -> addShow());

        final int COMMAND_LIST_SHOWS = 10;
        helpMap.put(COMMAND_LIST_SHOWS, "List All Shows");
        commandMap.put(COMMAND_LIST_SHOWS, () -> listShows());

        final int COMMAND_STORE_DATA = 11;
        helpMap.put(COMMAND_STORE_DATA, "Store Data");
        commandMap.put(COMMAND_STORE_DATA, () -> storeData());

        final int COMMAND_LOAD_DATA = 12;
        helpMap.put(COMMAND_LOAD_DATA, "Retrieve Data");
        commandMap.put(COMMAND_LOAD_DATA, () -> retrieveData());

        final int COMMAND_HELP = 13;
        helpMap.put(COMMAND_HELP, "Help");
        commandMap.put(COMMAND_HELP, () -> help());

        if (!helpMap.keySet().equals(commandMap.keySet())) { // Basically, one map can't include a key the other doesn't have.
            throw new IllegalStateException("The help map and command map do not have matching key sets. Both must have identical key sets.");
        }
    }

    /**
     * Whether or not data has already been loaded for this session.
     * Once data is loaded, it should not be possible to load it again.
     */
    static boolean dataRetrieved = false;


    public static void main(String args[]) {
        /* If we have saved data, prompt to load it. */
        if (Theater.hasData() && UserInterfacePrompts.promptYesOrNo("Would you like to load available application data before starting? ")) {
            Theater.retrieveData();
        }

        help(); // Show help at first launch.

        /* Loop until exit command is entered. Process other commands as entered. */
        int commandNumber;
        while ((commandNumber = UserInterfacePrompts.promptIntRange("Make a selection: ", 0, commandMap.values().size() - 1)) != 0) {
            commandMap.get(commandNumber).run();
        }

        /* Program exiting and saving */
        Theater.storeData();
    }

    /**
     * Asks for a client's information and sends a newly-created client object to the ClientList.
     *
     * @author Joseph T. Parsons
     */
    public static void addClient() {
        // Inputs
        String name = UserInterfacePrompts.promptLine("Client name? ");
        String address = UserInterfacePrompts.promptLine("Client address? ");
        long phone = UserInterfacePrompts.promptPhone("Phone number? ");

        // Add New Account Object to Client List
        Theater.getClientList().addAccount(new Client(name, address, phone));
    }


    /**
     * Asks for a client's ID and asks the client list to remove the client with the corresponding ID.
     *
     * @author Joseph T. Parsons
     * @modified Cory
     */
    public static void removeClient() {
        int id = UserInterfacePrompts.promptInt("Client ID? ");
        if (Theater.getClientList().checkShowDates(id)) {
	        if (Theater.getClientList().removeAccount(id)) {
	        	System.out.println("The client was removed.");
	        }
	        else {
	            System.out.println("The client could not be removed; does it exist?");
	        }
        }
        else {
            System.out.println("The client still has a show scheduled that hasn't ended yet");
        }
    }


    /**
     * Lists all clients in the ClientList.
     *
     * @author Joseph T. Parsons
     */
    public static void listClients() {
        System.out.println(Theater.getClientList());
    }


    /**
     * Asks for a customer's information and sends a newly-created customer object to the CustomerList.
     *
     * @author Eric
     * @throws ParseException
     */
    public static void addCustomer()  {
    	 // Inputs
        String name = UserInterfacePrompts.promptLine("Customer name? ");
        String address = UserInterfacePrompts.promptLine("Customer address? ");
        long phone = UserInterfacePrompts.promptPhone("Phone number? ");

        CreditCard creditCard = UserInterfacePrompts.promptCreditCard("Credit card number? ", "Credit card expiration (MMyyyy)? ", "This card is expired, please enter in a new credit card.");
        
        // Add New Account Object to Customer List
        Theater.getCustomerList().addAccount(new Customer(name, address, phone, creditCard));
    }


    public static void removeCustomer() {
    	//beginning implementation of this -Eric
    }


    public static void addCreditCard() {
        int id = UserInterfacePrompts.promptInt("Customer ID? ");
        CreditCard creditCard = UserInterfacePrompts.promptCreditCard("Credit card number? ", "Credit card expiration (MMyyyy)? ", "This card is expired, please enter in a new credit card.");
           
           
           // Add New Credit Card object to the specified Customer
           try {
        	   Theater.getCustomerList().getAccount(id).addCreditCard(creditCard);
           } catch (NullPointerException e) {
        	   System.out.println("Error, specified account does not exist, did you enter the correct account Id?");
           }
    	
    }


    public static void removeCreditCard() {
    	//beginning implementation of this -Eric
    }


    /**
     * Lists all customers in the CustomerList.
     */
    public static void listCustomers() {
        System.out.println(Theater.getCustomerList());
    }
    
    /**
     * Prompts for info about new show then creates it.
     * Adds newly created Show object to the ShowList.
     *
     *@author Cory
     */
    public static void addShow() {
        int id = (int) UserInterfacePrompts.promptInt("Client ID? ");
        if (Theater.getInstance().getClientList().validateAccount(id)) {
            String name = UserInterfacePrompts.promptLine("Show name? ");
            Date startDate = UserInterfacePrompts.promptShowDate("Start of Show (MM/DD/yyyy)? ");
            Date endDate = UserInterfacePrompts.promptShowDate("End of Show (MM/DD/yyyy)? ");
            // Add New Show Object to ShowList
            if (startDate.before(endDate)) {
	            if (Theater.getShowList().validShowDate(startDate, endDate)){
	            	Theater.getShowList().addShow(new Show(name, startDate, endDate));
	            }
	            else {
	            	System.out.println("These dates interfere with another show.");
	            }
            }
            else {
            	System.out.println("The show cant end before it starts.");
            }
        }
        else {
            System.out.println("The client doesnt exist?");
        }
    }

    /**
     * Lists all shows in the ShowList.
     * @author Cory
     */
    public static void listShows() {
        for (Show show : Theater.getInstance().getShowList()) {
            System.out.println(show);
        }
    }


    /**
     * Stores data by invoking Theater.storeData()
     *
     * @author Joseph T. Parsons
     */
    public static void storeData() {
        if (Theater.storeData()) {
            System.out.println("The data was successfully saved.");
        }
        else {
            System.out.println("The data could not be saved.");
        }
    }


    /**
     * Loads data by invoking Theater.retrieveData(). Ensures that data is not retrieved twice in a session.
     *
     * @author Joseph T. Parsons
     */
    public static void retrieveData() {
        if (dataRetrieved) {
            System.out.println("Application data has already been retrieved for this session.");
        }
        else if (Theater.retrieveData() == null) {
            System.out.println("The application's data could not be retrieved.");
        }
        else {
            System.out.println("The application's data was successfully loaded.");
        }
    }


    /**
     * Shows a list of commands that can be used.
     *
     * @author Joseph T. Parsons
     */
    public static void help() {
        for (Integer i : helpMap.keySet()) {
            System.out.println(i + ": " + helpMap.get(i));
        }
    }
}
