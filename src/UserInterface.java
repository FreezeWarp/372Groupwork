import java.util.Date;
import java.util.HashMap;
import java.util.Map;


/**
 * The interface used for all program interactions. Refer to {@link UserInterfacePrompts} for common input methods.
 *
 * @author  Eric Fulwiler, Daniel Johnson, Joseph Parsons, and Cory Stadther
 * @version 1.0
 * @since   2017-06-22
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
        while ((commandNumber = UserInterfacePrompts.promptIntRange("Make a selection (13 for Help): ", 0, commandMap.values().size() - 1)) != 0) {
            commandMap.get(commandNumber).run();
        }

        /* Save the program data on exit. */
        Theater.storeData();
    }


    /**
     * Asks for a client's information and sends a newly-created client object to the ClientList.
     */
    public static void addClient() {
        // Inputs
        String name = UserInterfacePrompts.promptLine("Client name? ");
        String address = UserInterfacePrompts.promptLine("Client address? ");
        long phone = UserInterfacePrompts.promptPhone("Phone number? ");

        try {
            // Add New Account Object to Client List
            Client client = new Client(name, address, phone);
            Theater.getClientList().addAccount(client);
        } catch (AccountPhoneNumberOutOfRangeException ex) {
            System.out.println("The phone number was out-of-range. The client was not added. This may be an internal error.");
        }
    }


    /**
     * Asks for a client's ID and asks the client list to remove the client with the corresponding ID.
     */
    public static void removeClient() {
        Client client = Theater.getClientList().getAccount(UserInterfacePrompts.promptInt("Client ID? "));

        if (client == null) {
            System.out.println("The client does not exist.");
        }
        else {
            try {
                if (Theater.getClientList().removeClient(client.getId())) {
                    System.out.println("The client was removed.");
                } else {
                    System.out.println("The client could not be removed.");
                }
            } catch (ClientListOngoingShowsException ex) {
                System.out.println("The client still has a show scheduled that hasn't ended yet.");
            }
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
     */
    public static void addCustomer()  {
    	 // Inputs
        String name = UserInterfacePrompts.promptLine("Customer name? ");
        String address = UserInterfacePrompts.promptLine("Customer address? ");
        long phone = UserInterfacePrompts.promptPhone("Phone number? ");

        CreditCard creditCard = UserInterfacePrompts.promptCreditCard("Credit card number? ", "Credit card expiration (MMyyyy)? ");

        if (creditCard == null) {
            System.out.println("An invalid credit card was detected. Unable to add the customer account.");
        }
        else {
            try {
                if (Theater.getCustomerList().addAccount(new Customer(name, address, phone, creditCard))) {
                    System.out.println("The customer account was added.");
                }
                else {
                    System.out.println("The customer account could not be added.");
                }
            } catch (AccountPhoneNumberOutOfRangeException ex) {
                System.out.println("The phone number was out-of-range. The customer was not added. This may be an internal error.");
            }
        }
        
    }


    /**
     * Asks for a customer's ID and asks the customer list to remove the customer with the corresponding ID.
     */
    public static void removeCustomer() {
    	Customer customer = Theater.getCustomerList().getAccount(UserInterfacePrompts.promptInt("Customer ID? "));

        if (customer == null) {
            System.out.println("Error, specified customer does not exist, did you enter the correct account ID?");
        } 
        else {
        	customer.removeCreditCards(); 
        	    
        	if (Theater.getCustomerList().removeAccount(customer.getId())) {
        	    System.out.println("The customer was removed, along with all associated credit cards.");
        	}
        	else {
        	    System.out.println("The customer could not be deleted.");
        	}
 
	    }   
    }


    /**
     * Asks for a customer's credit card information and stores the newly created CreditCard object corresponding to the entered customer ID.
     */
    public static void addCreditCard() {
        Customer customer = Theater.getCustomerList().getAccount(UserInterfacePrompts.promptInt("Customer ID? "));
           
        //adds a new credit card to the customer's account, if the customer account exists
        if (customer == null) {
        	 System.out.println("Error, specified customer does not exist, did you enter the correct account ID?");
        }
        else {
            CreditCard creditCard = UserInterfacePrompts.promptCreditCard("Credit card number? ", "Credit card expiration (MMyyyy)? ");

            if (creditCard == null) {
                System.out.println("An invalid credit card was detected. Unable to add the credit card.");
            }
     	    else if (customer.addCreditCard(creditCard)) {
                System.out.println("The credit card was added to the account.");
            }
     	    else {
     		    System.out.println("Could not add credit card to the customer's account.");
     	    }
     	   
        }

    }


    /**
     * Asks for a credit card number to be entered, then deletes the corresponding credit card from whichever customer added it.
     */
    public static void removeCreditCard() {
    	 Customer customer = Theater.getCustomerList().getAccount(UserInterfacePrompts.promptInt("Customer ID of the credit card holder? "));
    	 
    	 if (customer == null) {
             System.out.println("Error, specified customer does not exist, did you enter the correct account ID?");
         } 
    	 else {
       	    try {
                if (customer.removeCreditCard(UserInterfacePrompts.promptCreditCardNumber("Credit card number?"))) {
                    System.out.println("The credit card was removed.");
                }
                else {
                    System.out.println("The customer's credit card could not be deleted.");
                }
            } catch (CustomerMinimumCreditCardsException ex) {
                System.out.println("Cannot delete the last credit card a user has.");
            }
 	    }   	
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
     */
    public static void addShow() {
        Client client = Theater.getClientList().getAccount(UserInterfacePrompts.promptInt("Client ID? "));

        if (client == null) {
            System.out.println("Error, specified client does not exist, did you enter the correct account ID?");
        }
        else {
            String name = UserInterfacePrompts.promptLine("Show name? ");
            Date startDate = UserInterfacePrompts.promptShowDate("Start of Show (MM/DD/yyyy)? ");
            Date endDate = UserInterfacePrompts.promptShowDate("End of Show (MM/DD/yyyy)? ");

            try {
                Show show = new Show(client, name, startDate, endDate);

                try {
                    if (Theater.getShowList().addShow(show)) {
                        System.out.println("The show was added.");
                    }
                    else {
                        System.out.println("The show could not be added.");
                    }
                }
                catch (ShowConflictException ex) {
                    System.out.println("These dates interfere with another show.");
                }
            } catch (ShowDateMismatchException ex) {
                System.out.println("The show cannot end before it starts.");
            }
        }
    }


    /**
     * Lists all shows in the ShowList.
     */
    public static void listShows() {
        System.out.println(Theater.getShowList());
    }


    /**
     * Stores data by invoking Theater.storeData()
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
     */
    public static void help() {
        for (Integer i : helpMap.keySet()) {
            System.out.println(i + ": " + helpMap.get(i));
        }
    }
}
