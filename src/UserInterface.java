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

        final int COMMAND_SELL_REGULAR_TICKET = 13;
        helpMap.put(COMMAND_SELL_REGULAR_TICKET, "Sell Regular Tickets");
        commandMap.put(COMMAND_SELL_REGULAR_TICKET, () -> sellTickets(TicketType.Ticket));

        final int COMMAND_SELL_ADVANCE_TICKET = 14;
        helpMap.put(COMMAND_SELL_ADVANCE_TICKET, "Sell Advance Tickets");
        commandMap.put(COMMAND_SELL_ADVANCE_TICKET, () -> sellTickets(TicketType.AdvanceTicket));

        final int COMMAND_SELL_STUDENT_TICKET = 15;
        helpMap.put(COMMAND_SELL_STUDENT_TICKET, "Sell Student Tickets");
        commandMap.put(COMMAND_SELL_STUDENT_TICKET, () -> sellTickets(TicketType.StudentAdvanceTicket));

        final int COMMAND_PAY_CLIENT = 16;
        helpMap.put(COMMAND_PAY_CLIENT, "Pay Client");
        commandMap.put(COMMAND_PAY_CLIENT, () -> payClient());

        final int COMMAND_HELP = 18;
        helpMap.put(COMMAND_HELP, "Help");
        commandMap.put(COMMAND_HELP, () -> help());

        if (!helpMap.keySet().equals(commandMap.keySet())) { // Basically, one map can't include a key the other doesn't have.
            throw new IllegalStateException("The help map and command map do not have matching key sets. Both must have identical key sets.");
        }
    }


    public static void main(String args[]) {
        /* If we have saved data, prompt to load it. */
        if (Theater.hasData() && UserInterfacePrompts.promptYesOrNo("Would you like to load available application data before starting? ")) {
            retrieveData();
        }
        else {
            // For easier testing (feel free to add your own entries after the needed refactoring into Theater).
            Theater.addClient("Bobby Johnson", "123 Downing St.", 4445559100l); // client ID = 0
            Theater.addShow(0, "Cats, on Ice!", new Date(199, 5, 11), new Date(199, 5, 16), 10);
            Theater.addShow(0, "Dogs, on Grass!", new Date(199, 5, 21), new Date(199, 5, 25), 20);
            Theater.addShow(0, "Birds, on Planes!", new Date(199, 6, 3), new Date(199, 6, 25), 30);
            Theater.addShow(0, "You, in the Audience!", new Date(199, 7, 0), new Date(199, 7, 20), 40);
        }

        help(); // Show help at first launch.

        /* Loop until exit command is entered. Process other commands as entered. */
        int commandNumber;
        while ((commandNumber = UserInterfacePrompts.promptIntRange(System.getProperty("line.separator") + "Make a selection (18 for Help): ", 0, commandMap.values().size() - 1)) != 0) {
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

        switch (Theater.addClient(name, address, phone)) {
            case FAILURE:
                System.out.println("The client could not be added.");
                break;

            case SUCCESS:
                System.out.println("The client was added.");
                break;

            case PHONE_NUMBER_OUT_OF_RANGE:
                System.out.println("The phone number was out-of-range. The client was not added. This may be an internal error.");
                break;

            default:
                System.out.println("An unknown status code was returned.");
                break;
        }
    }


    /**
     * Asks for a client's ID and asks the client list to remove the client with the corresponding ID.
     */
    public static void removeClient() {
        switch (Theater.removeClient(UserInterfacePrompts.promptInt("Client ID? "))) {
            case NOEXIST:
                System.out.println("The client does not exist.");
                break;

            case SUCCESS:
                System.out.println("The client was removed.");
                break;

            case FAILURE:
                System.out.println("The client could not be removed.");
                break;

            case ONGOING_SHOW:
                System.out.println("The client still has a show scheduled that hasn't ended yet.");
                break;

            default:
                System.out.println("An unknown status code was returned.");
                break;
        }
    }


    /**
     * Lists all clients in the ClientList.
     */
    public static void listClients() {
        System.out.println(Theater.getClients());
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
            } catch (Account.AccountPhoneNumberOutOfRangeException ex) {
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
            System.out.println("Error, specified client does not exist. Did you enter the correct account ID?");
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
            System.out.println("Error, specified client does not exist. Did you enter the correct account ID?");
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
        int customerId = UserInterfacePrompts.promptInt("Customer ID of the credit card holder? ");

        if (!Theater.getCustomerList().validateAccount(customerId)) {
            System.out.println("Error, specified client does not exist. Did you enter the correct account ID?");
        }
        else {
            long creditCardNumber = UserInterfacePrompts.promptCreditCardNumber("Credit card number? ");

            switch (Theater.removeCreditCard(customerId, creditCardNumber)) {
                case SUCCESS:
                    System.out.println("The credit card was removed.");
                    break;

                case FAILURE:
                    System.out.println("The customer's credit card could not be deleted.");
                    break;

                case LAST_CARD:
                    System.out.println("Cannot delete the last credit card a user has.");
                    break;

                default:
                    System.out.println("An unknown status code was returned.");
                    break;
            }
        }
    }


    /**
     * Lists all customers in the CustomerList.
     */
    public static void listCustomers() {
        System.out.println(Theater.getCustomers());
    }


    /**
     * Prompts for info about new show then creates it.
     * Adds newly created Show object to the ShowList.
     */
    public static void addShow() {
        int clientId = UserInterfacePrompts.promptInt("Client ID? ");

        if (!Theater.getClientList().validateAccount(clientId)) {
            System.out.println("Error, specified client does not exist. Did you enter the correct account ID?");
        }
        else {
            String name = UserInterfacePrompts.promptLine("Show name? ");
            Date startDate = UserInterfacePrompts.promptShowDate("Start of Show (MM/DD/yyyy)? ");
            Date endDate = UserInterfacePrompts.promptShowDate("End of Show (MM/DD/yyyy)? ");
            double ticketPrice = UserInterfacePrompts.promptDouble("Ticket price?");

            switch (Theater.addShow(clientId, name, startDate, endDate, ticketPrice)) {
                case SUCCESS:
                    System.out.println("The show was added.");
                    break;

                case FAILURE:
                    System.out.println("The show could not be added.");
                    break;

                case SHOW_CONFLICT:
                    System.out.println("These dates interfere with another show.");
                    break;

                case DATE_MISMATCH:
                    System.out.println("The show cannot end before it starts.");
                    break;

                default:
                    System.out.println("An unknown status code was returned.");
                    break;
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
        switch (Theater.storeData()) {
            case SUCCESS:
                System.out.println("The data was successfully saved.");
                break;

            case FAILURE:
                System.out.println("The data could not be saved.");
                break;

            default:
                System.out.println("An unknown status code was returned.");
                break;
        }
    }


    /**
     * Loads data by invoking Theater.retrieveData(). Ensures that data is not retrieved twice in a session.
     */
    public static void retrieveData() {
        switch (Theater.retrieveData()) {
            case FAILURE:
                System.out.println("The application's data could not be retrieved.");
                break;

            case SUCCESS:
                System.out.println("The application's data was successfully loaded.");
                break;

            case ALREADY_LOADED:
                System.out.println("The application's data can not be loaded twice in a session.");
                break;

            default:
                System.out.println("An unknown status code was returned.");
                break;
        }
    }

    /**
     * Sells tickets, but it doesn't update client or customer object, my man
     */
//TODO need to update client/customer objects
    public static void sellTickets(TicketType t) {
        int quantity = UserInterfacePrompts.promptInt("Quantity? ");
        if (quantity < 1) {
            System.out.println("Please enter a positive integer for quantity.");
        } else {
        Customer customer = Theater.getCustomerList().getAccount(UserInterfacePrompts.promptInt("Customer ID? "));


        //adds a new credit card to the customer's account, if the customer account exists
        if (customer == null) {
            System.out.println("Error, specified client does not exist. Did you enter the correct account ID?");
        }
        else {
            CreditCard creditCard = customer.getCreditCard();
            Date showDate = UserInterfacePrompts.promptShowDate("Date of the show? (MM/DD/yyyy)? ");
            Show show = ShowList.getShow(showDate); // Get a show from the date, I think. I haven't tested.
            
            //THIS IS ALL TEMP STUFF NEEDS TO BE REFACTORED//
            
            Client client = Theater.getShowList().checkShowForTicketSales(showDate); //get the client the show belongs to.
            if (client != null) //client will be null if no show exists during this time
	            {
	            	Theater.sellTickets(t, quantity, customer, creditCard, showDate);
	            	//client.adjustBalance(100);//TODO Get the correct balance adjustment
	            }
            else {System.out.println("No shows exist durring this time");}
            }
        }

    public static void payClient() {
        Client client = Theater.getClientList().getAccount(UserInterfacePrompts.promptInt("Client ID? "));
        if (client == null) {
            System.out.println("Error, specified client does not exist. Did you enter the correct account ID?");
        } else {
            System.out.println("The client's balance is :$" + client.getBalance());
            double compare = client.getBalance();
            if (compare == 0) {
                System.out.println("We don't owe them anything!");
            } else {
                double payOff = UserInterfacePrompts.promptDouble("How much are we paying the client?");
                if (compare < payOff) {
                    System.out.println("This is more than we owe them.");
                } else {
                    double negative = payOff * -1;
                    client.adjustBalance(negative);
                    System.out.println("The client's new balance is :$" + client.getBalance());
                }


            }
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
