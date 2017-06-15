import java.util.Date;

/**
 * Created by joseph on 12/06/17.
 */
public class Interface {
    /**
     * Text strings representing actions that can be shown to users, as part of help text.
     */
    static String commands[] = {
        "Exit the application.",
        "Add Client.",
        "Remove Client.",
        "List all Clients.",
        "Add Customer.",
        "Remove Customer.",
        "Add a Credit Card.",
        "Remove a Credit Card.",
        "List all Customers.",
        "Add a Show/Play.",
        "List All Shows",
        "Store Data",
        "Retrieve Data",
        "Help"
    };

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
        while ((commandNumber = InterfacePrompts.promptIntRange("Make a selection: ", 0, commands.length - 1)) != 0) {
            switch (commandNumber) {
                case 1: addClient(); break;
                case 2: removeClient(); break;
                case 3: listClients(); break;
                case 4: addCustomer(); break;
                case 5: removeCustomer(); break;
                case 6: addCreditCard(); break;
                case 7: removeCreditCard(); break;
                case 8: listCustomers(); break;
                case 9: addShow(); break;
                case 10: listShows(); break;
                case 11: Theater.storeData(); break;
                case 12: Theater.retrieveData(); break;
                case 13: help(); break;
            }
        }

        /* We're about to exit, so prompt the user if they'd like to save data or not. */
        if (InterfacePrompts.promptYesOrNo("Would you like to save the application data before exiting? ")) {
            Theater.storeData();
        }
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
        Theater.getInstance().getClientList().addAccount(new Client(name, address, phone));
    }


    /**
     * Asks for a client's ID and asks the client list to remove the client with the corresponding ID.
     *
     * @author Joseph
     */
    public static void removeClient() {
        int id = (int) InterfacePrompts.promptInt("Client ID? ");

        if (Theater.getInstance().getClientList().removeAccount(id)) {
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
        System.out.println(Theater.getInstance().getClientList());
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
        long creditCardNo = InterfacePrompts.promptCreditCard("Credit card number? ");
        Date expiryDate = InterfacePrompts.promptCreditCardExpiry("Credit card expiration (MMyyyy)? "); 
        CreditCard creditCard = new CreditCard(creditCardNo, expiryDate);
        
     // Add New Account Object to Customer List
        Theater.getInstance().getCustomerList().addAccount(new Customer(name, address, phone, creditCard));
    }


    public static void removeCustomer() {
    }


    public static void addCreditCard() {
        int id = (int) InterfacePrompts.promptInt("Customer ID? ");
        long creditCardNo = InterfacePrompts.promptCreditCard("Credit card number? ");
        Date expiryDate = InterfacePrompts.promptCreditCardExpiry("Credit card expiration (MMyyyy)? ");

        while (expiryDate.before(new Date())) { //compares the expiry date of the CC with the current date
            System.out.println("This card is expired, please enter in a new credit card.");
            creditCardNo = InterfacePrompts.promptCreditCard("Credit card number? ");
            expiryDate = InterfacePrompts.promptCreditCardExpiry("Credit card expiration (MMyyyy)? ");
        }

        CreditCard creditCard = new CreditCard(creditCardNo, expiryDate);
           
           
           // Add New Credit Card object to the specified Customer
           try {
        	   Theater.getInstance().getCustomerList().getAccount(id).addCreditCard(creditCard);
           } catch (NullPointerException e) {
        	   System.out.println("Error, specified account does not exist, did you enter the correct account Id?");
           }
    	
    }


    public static void removeCreditCard() {

    }


    /**
     * Lists all customers in the CustomerList.
     */
    public static void listCustomers() {
        System.out.println(Theater.getInstance().getCustomerList());
    }


    public static void addShow() {

    }


    public static void listShows() {

    }


    /**
     * Shows a list of commands that can be used.
     * @author Joseph
     */
    public static void help() {
        for (int i = 0; i < commands.length; i++) {
            System.out.println(i + ": " + commands[i]);
        }
    }
}
