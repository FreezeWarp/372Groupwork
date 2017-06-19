import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by joseph on 12/06/17.
 */
public class Interface {
    /**
     * A Map of callable commands that are supported by the interface, keyed by the command used to invoke them.
     */
    static final Map<Integer, Runnable> commandMap = new HashMap<Integer, Runnable>();

    /**
     * A map of descriptions for {@link Interface#commandMap}.
     */
    static final Map<Integer, String> helpMap = new HashMap<Integer, String>();

    /*
     * A one-stop initialisation of all values for both commandMap and helpMap.
     */
    static {
        helpMap.put(0, "Exit the application.");
        commandMap.put(0, () -> {}); // Do nothing -- this is handled specially inside of the command loop.

        helpMap.put(1, "Add Client.");
        commandMap.put(1, () -> addClient());

        helpMap.put(2, "Remove Client.");
        commandMap.put(2, () -> removeClient());

        helpMap.put(3, "List all Clients.");
        commandMap.put(3, () -> listClients());

        helpMap.put(4, "Add Customer.");
        commandMap.put(4, () -> addCustomer());

        helpMap.put(5, "Remove Customer.");
        commandMap.put(5, () -> removeCustomer());

        helpMap.put(6, "Add a Credit Card.");
        commandMap.put(6, () -> addCreditCard());

        helpMap.put(7, "Remove a Credit Card.");
        commandMap.put(7, () -> removeCreditCard());

        helpMap.put(8, "List all Customers.");
        commandMap.put(8, () -> listCustomers());

        helpMap.put(9, "Add a Show/Play.");
        commandMap.put(9, () -> addShow());

        helpMap.put(10, "List All Shows");
        commandMap.put(10, () -> listShows());

        helpMap.put(11, "Store Data");
        commandMap.put(11, () -> Theater.storeData());

        helpMap.put(12, "Retrieve Data");
        commandMap.put(12, () -> Theater.retrieveData());

        helpMap.put(13, "Help");
        commandMap.put(13, () -> help());

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
        if (Theater.hasData() && InterfacePrompts.promptYesOrNo("Would you like to load available application data before starting? ")) {
            Theater.retrieveData();
        }

        help(); // Show help at first launch.

        /* Loop until exit command is entered. Process other commands as entered. */
        int commandNumber;
        while ((commandNumber = InterfacePrompts.promptIntRange("Make a selection: ", 0, commandMap.values().size() - 1)) != 0) {
            commandMap.get(commandNumber).run();
        }

        /* Program exiting and saving */
        Theater.storeData();
    }

    /**
     * Asks for a client's information and sends a newly-created client object to the ClientList.
     *
     * @author Joseph
     */
    public static void addClient() {
        // Inputs
        String name = InterfacePrompts.promptLine("Client name? ");
        String address = InterfacePrompts.promptLine("Client address? ");
        long phone = InterfacePrompts.promptPhone("Phone number? ");

        // Add New Account Object to Client List
        Theater.getClientList().addAccount(new Client(name, address, phone));
    }


    /**
     * Asks for a client's ID and asks the client list to remove the client with the corresponding ID.
     *
     * @author Joseph
     */
    public static void removeClient() {
        int id = InterfacePrompts.promptInt("Client ID? ");

        if (Theater.getClientList().removeAccount(id)) {
            System.out.println("The client was removed.");
        }
        else {
            System.out.println("The client could not be removed; does it exist?");
        }
    }


    /**
     * Lists all clients in the ClientList.
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
        String name = InterfacePrompts.promptLine("Customer name? ");
        String address = InterfacePrompts.promptLine("Customer address? ");
        long phone = InterfacePrompts.promptPhone("Phone number? ");

        CreditCard creditCard = InterfacePrompts.promptCreditCard("Credit card number? ", "Credit card expiration (MMyyyy)? ", "This card is expired, please enter in a new credit card.");
        
     // Add New Account Object to Customer List
        Theater.getCustomerList().addAccount(new Customer(name, address, phone, creditCard));
    }


    public static void removeCustomer() {
    	//beginning implementation of this -Eric
    }


    public static void addCreditCard() {
        int id = InterfacePrompts.promptInt("Customer ID? ");
        CreditCard creditCard = InterfacePrompts.promptCreditCard("Credit card number? ", "Credit card expiration (MMyyyy)? ", "This card is expired, please enter in a new credit card.");
           
           
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


    public static void addShow() {
        int id = (int) InterfacePrompts.promptInt("Client ID? ");
        if (Theater.getInstance().getClientList().validateAccount(id)) {
            String name = InterfacePrompts.promptLine("Show name? ");
            Date startDate = InterfacePrompts.promptShowDate("Start of Show (MM/DD/yyyy)? "); 
            Date endDate = InterfacePrompts.promptShowDate("End of Show (MM/DD/yyyy)? "); 
            // Add New Show Object to ShowList
            Theater.getShowList().addShow(new Show(id ,name, startDate, endDate)); //TODO: Error with this, need to modify the ShowList class to fix
        }
        else {
            System.out.println("The client doesnt exist?");
        }
    }


    public static void listShows() {
        for (Show show : Theater.getInstance().getShowList()) {
            System.out.println(show);
        }
    }


    /**
     * Shows a list of commands that can be used.
     * @author Joseph
     */
    public static void help() {
        for (Integer i : helpMap.keySet()) {
            System.out.println(i + ": " + helpMap.get(i));
        }
    }
}
