import java.util.Date;
import java.util.HashMap;
import java.util.List;
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
        
        final int COMMAND_PRINT_ALL_TICKETS = 17;
        helpMap.put(COMMAND_PRINT_ALL_TICKETS, "Print All Tickets");
        commandMap.put(COMMAND_PRINT_ALL_TICKETS, () -> printAllTickets());

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

        switch (Theater.addCustomer(name, address, phone, creditCard)) {
            case CREDIT_CARD_DUPLICATE:
            	System.out.print("The credit card entered is already in use for another account.");
            	break;
            	
            case CREDIT_CARD_INVALID:
            	System.out.println("An invalid credit card was detected. Unable to add the customer account.");
            	break;
            	
            case FAILURE:
                System.out.println("The customer could not be added.");
                break;

            case SUCCESS:
                System.out.println("The customer was added.");
                break;

            case PHONE_NUMBER_OUT_OF_RANGE:
                System.out.println("The phone number was out-of-range. The customer was not added. This may be an internal error.");
                break;

            default:
                System.out.println("An unknown status code was returned.");
                break;
        }

    }


    /**
     * Asks for a customer's ID and asks the customer list to remove the customer with the corresponding ID.
     */
    public static void removeCustomer() {
        int customerId = UserInterfacePrompts.promptInt("Customer ID of the customer whose account is to be deleted? ");
    	
    	if (!Theater.getCustomerList().validateAccount(customerId)) {
            System.out.println("Error, specified customer does not exist. Did you enter the correct account ID?");
        }
    	else {
    		switch (Theater.removeCustomer(customerId)) {
                case NOEXIST:
           	        System.out.println("The customer does not exist");
        	        break;
        	
                case FAILURE:
                    System.out.println("The customer could not be removed.");
                    break;

                case SUCCESS:
                    System.out.println("The customer was removed.");
                    break;

                case CREDIT_CARD_FAILURE:
        	        System.out.println("The credit cards associated with the customer account could not be deleted. The customer was not deleted. This may be an internal error.");
        	        break;
        	
                default:
                    System.out.println("An unknown status code was returned.");
                    break;
           }
       }    
    }


    /**
     * Asks for a customer's credit card information and stores the newly created CreditCard object corresponding to the entered customer ID.
     */
    public static void addCreditCard() {
    	int customerId = UserInterfacePrompts.promptInt("Customer ID of the credit card holder? ");
        CreditCard creditCard = UserInterfacePrompts.promptCreditCard("Credit card number? ", "Credit card expiration (MMyyyy)? ");
    	
    	if (!Theater.getCustomerList().validateAccount(customerId)) {
            System.out.println("Error, specified customer does not exist. Did you enter the correct account ID?");
        }
    	else {
            switch (Theater.addCreditCard(customerId, creditCard)) {
                case NOEXIST:
               	    System.out.println("The customer does not exist");
            	    break;
            	
                case FAILURE:
                    System.out.println("The credit card could not be added.");
                    break;

                case SUCCESS:
                    System.out.println("The credit card was added.");
                    break;

                case CREDIT_CARD_DUPLICATE:
            	    System.out.println("The credit card entered is already in use.");
            	    break;
            	
                default:
                    System.out.println("An unknown status code was returned.");
                    break;
            }
    	}
    }


    /**
     * Asks for a credit card number to be entered, then deletes the corresponding credit card from whichever customer added it.
     */
    public static void removeCreditCard() {
    	
        int customerId = UserInterfacePrompts.promptInt("Customer ID of the credit card holder? ");
        
    	if (!Theater.getCustomerList().validateAccount(customerId)) {
    	    System.out.println("Error, specified customer does not exist. Did you enter the correct account ID?");
    	 }
    	 else {
    	     long creditCardNumber = UserInterfacePrompts.promptCreditCardNumber("Credit card number? ");
    	     switch (Theater.removeCreditCard(customerId, creditCardNumber)) {
    	         case NOEXIST:
                     System.out.println("The credit card entered does not exist.");
                     break;

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
     * Sells tickets, but it doesn't update the customer object and has a ghetto way of checking showDate.  
     */
    public static void sellTickets(TicketType t) {
        int quantity = UserInterfacePrompts.promptIntRange("Quantity? ", 1, Integer.MAX_VALUE);
        int customerId = UserInterfacePrompts.promptInt("Customer ID? ");
        long creditCardNumber = UserInterfacePrompts.promptCreditCardNumber("Customer Credit Card? ");
        Date showDate = UserInterfacePrompts.promptShowDate("Date of the show? (MM/DD/yyyy)? ");

        switch (Theater.sellTickets(t, quantity, customerId, creditCardNumber, showDate)) {
            case INVALID_CUSTOMER_ID:
                System.out.println("The entered customer ID does not exist.");
                break;

            case INVALID_SHOW_DATE:
                System.out.println("The entered show date does not correspond with a valid show.");
                break;

            case INVALID_CREDIT_CARD_NUMBER:
                System.out.println("The entered credit card number is invalid.");
                break;

            default:
                System.out.println("An unknown status code was returned.");
                break;
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
                double payment = UserInterfacePrompts.promptDouble("How much are we paying the client?");
                if (compare < payment) {
                    System.out.println("This is more than we owe them.");
                } else {
                    double negative = payment * -1;
                    client.adjustBalance(negative);
                    System.out.println("The client's new balance is :$" + client.getBalance());
                }


            }
        }
    }
    
    /**
     * Lists all tickets on a given day from TicketList.
     */
    public static void printAllTickets() {
        Date date = UserInterfacePrompts.promptTicketDate("Date of Tickets to show? (MM/DD/yyyy)? ");
        List<Ticket> t = Theater.getTicketList(date);

        //I know the .replace() isn't ideal.  But it does look better when you print.  Anyone have a better way to do this?
        //If you print just t you get a list that looks like this [ticket1, ticket2, ticket3]

        //if (t!=null) System.out.println(t);
        if (t!= null)
            System.out.println(t.toString().replace("]", "").replace("[", ""));
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
