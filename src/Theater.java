import java.io.*;
import java.util.Date;



/**
 * A façade (and singleton) for the Theater.
 * This should be the main entry point for all interface actions; in a sense, it is the API layer.
 * Much of the overall functionality is actually implemented through getClientList, getCustomer, and getShowList, however.
 *
 * Note a couple of design decisions:
 ** The various singleton hashmaps are never interacted with directly; instead, their instances are always returned through the Theater façade. This is done to lower coupling.
 ** Classes that duplicate functionality have that functionality implemented in generic superclasses as much as possible. This is done to maximise cohesion and reduce bugs (if one class has a bug, the others will as well, making detection easier).
 ** All operations that perform validation in ClientList, CustomerList, and ShowList will throw checked exceptions for the validation instead of integer status codes.
 ** Theater itself acts as a façade for UserInterface operations, catching exceptions and returning enumerated status codes to UserInterface.
 *
 * @author  Eric Fulwiler, Daniel Johnson, Joseph T. Parsons, and Cory Stadther
 * @version 2.0
 * @since   2017-July-08
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
     * Determines if the persistence file exists with data.
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
     * @return A code from {@link STORE_DATA_STATUS}.
     */
    public static STORE_DATA_STATUS storeData() {
        try {
            FileOutputStream out = new FileOutputStream(persistenceFile);
            ObjectOutputStream oos = new ObjectOutputStream(out);

            oos.writeObject(INSTANCE);
            oos.writeObject(ShowList.getInstance());
            oos.writeObject(CustomerList.getInstance());
            oos.writeObject(ClientList.getInstance());
            oos.writeObject(CreditCardList.getInstance());
            oos.writeObject(TicketList.getInstance());
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
     * @return A code from {@link RETRIEVE_DATA_STATUS}.
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
                ois.readObject();
                ois.readObject();
                ois.readObject();
                ois.readObject();
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

    /**
     * Adds a {@link Client} to the {@link ClientList}.
     *
     * @param name The name of the client.
     * @param address The address of the client.
     * @param phone The phone # of the client.
     *
     * @return A code from {@link ADD_CLIENT_STATUS}.
     */
    public static ADD_CLIENT_STATUS addClient(String name, String address, long phone) {
        try {
            // Add New Account Object to Client List.
            Client client = new Client(name, address, phone);

            if (getClientList().addAccount(client)) {
                return ADD_CLIENT_STATUS.SUCCESS;
            }
            else {
                return ADD_CLIENT_STATUS.FAILURE;
            }
        } catch (Client.AccountPhoneNumberOutOfRangeException ex) {
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

    /**
     * Removes a client from the {@link ClientList}.
     *
     * @param clientId The ID of the client to be removed.
     *
     * @return A code from {@link REMOVE_CLIENT_STATUS}.
     */
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
            } catch (ClientList.ClientListOngoingShowsException ex) {
                return REMOVE_CLIENT_STATUS.ONGOING_SHOW;
            }
        }
    }



    /**
     * @return An iterable class to be used for iterating through the client list.
     */
    public static Iterable<Client> getClients() {
        return getClientList();
    }
    


    /**
     * Returned codes used from {@link Theater#addCustomer(String, String, long, CreditCard)}.
     */
    enum ADD_CUSTOMER_STATUS {
    	/**
         * The credit card a user tried to enter is invalid. */
        CREDIT_CARD_INVALID,
        /**
         * Generic failure occurred. */
        FAILURE,
        /**
         * Method ended successfully. */
        SUCCESS,
        /**
         * The phone number used is out of the valid range of phone numbers. */
        PHONE_NUMBER_OUT_OF_RANGE,
        /**
         * The credit card already exists in the CreditCardList. */
        CREDIT_CARD_DUPLICATE
    }

    /**
     * Adds a {@link Customer} to the {@link CustomerList}.
     *
     * @param name The name of the customer.
     * @param address The address of the customer.
     * @param phone The phone # of the customer.
     *
     * @return A code from {@link ADD_CUSTOMER_STATUS}.
     */
    public static ADD_CUSTOMER_STATUS addCustomer(String name, String address, long phone, CreditCard creditCard) {
        if (creditCard == null) {
            return ADD_CUSTOMER_STATUS.CREDIT_CARD_INVALID;
        }
        else {
            try {
                // Add New Account Object to Customer List.
                Customer customer = new Customer(name, address, phone, creditCard);

                if (getCustomerList().addAccount(customer)) {
                    return ADD_CUSTOMER_STATUS.SUCCESS;
                }
                else {
                    return ADD_CUSTOMER_STATUS.FAILURE;
                }
            } catch (Customer.AccountPhoneNumberOutOfRangeException ex) {
                return ADD_CUSTOMER_STATUS.PHONE_NUMBER_OUT_OF_RANGE;
            } catch(Customer.CustomerDuplicateCardException ex) {
        	    return ADD_CUSTOMER_STATUS.CREDIT_CARD_DUPLICATE;
            }
        }
    }



    /**
     * Returned codes used from {@link Theater#removeCustomer(int)}.
     */
    enum REMOVE_CUSTOMER_STATUS {
        /**
         * The customer to be removed does not exist. */
        NOEXIST,
        /**
         * Generic failure occurred. */
        FAILURE,
        /**
         * Method ended successfully. */
        SUCCESS,
        /**
         * The method failed because a credit card associated with the customer's account could not be deleted. */
        CREDIT_CARD_FAILURE
    }

    /**
     * Removes a customer from the {@link CustomerList}.
     *
     * @param customerId The ID of the customer to be removed.
     *
     * @return A code from {@link REMOVE_CUSTOMER_STATUS}.
     */
    public static REMOVE_CUSTOMER_STATUS removeCustomer(int customerId) {
        Customer customer = getCustomerList().getAccount(customerId);

        if (customer == null) {
            return REMOVE_CUSTOMER_STATUS.NOEXIST;
        }
        else {
            try {
                if (getCustomerList().removeAccount(customer.getId()) && customer.removeCreditCards()) {
                    return REMOVE_CUSTOMER_STATUS.SUCCESS;
                }
                else {
                    return REMOVE_CUSTOMER_STATUS.FAILURE;
                }
            } catch (Customer.CustomerCouldNotDeleteCreditCardsException ex) {
                return REMOVE_CUSTOMER_STATUS.CREDIT_CARD_FAILURE;
            }
        }
    }



    /**
     * @return An iterable class to be used for iterating through the customer list.
     */
    public static Iterable<Customer> getCustomers() {
        return getCustomerList();
    }


    
    /**
     * Returned codes used from {@link Theater#addCreditCard(int, CreditCard)}.
     */
    enum ADD_CREDIT_CARD_STATUS {
    	/**
         * The customer to have a credit card added does not exist */
        NOEXIST,
        /**
         * Generic failure occurred. */
        FAILURE,
        /**
         * Method ended successfully. */
        SUCCESS,
        /**
         * The credit card already exists in the CreditCardList. */
        CREDIT_CARD_DUPLICATE
    }

    /**
     * Adds a {@link CreditCard} to the {@link CreditCardList}.
     *
     * @param customerId The ID of the customer whose {@link CreditCard} is being deleted.
     *
     * @return A code from {@link ADD_CREDIT_CARD_STATUS}.
     */
    public static ADD_CREDIT_CARD_STATUS addCreditCard(int customerId, CreditCard creditCard) {
    	Customer customer = Theater.getCustomerList().getAccount(customerId);
  
    	if (customer == null) {
    		return ADD_CREDIT_CARD_STATUS.NOEXIST;
    	}
    	else {
            try {
				if (customer.addCreditCard(creditCard)) {
				    return ADD_CREDIT_CARD_STATUS.SUCCESS;
				}
				else {
				    return ADD_CREDIT_CARD_STATUS.FAILURE;
				}
			} catch (Customer.CustomerDuplicateCardException e) {
			    return ADD_CREDIT_CARD_STATUS.CREDIT_CARD_DUPLICATE;
			}
        }
    }



    /**
     * Returned codes used from {@link Theater#removeCreditCard(int, long)}.
     */
    enum REMOVE_CREDIT_CARD_STATUS {
        /**
         * The {@link CreditCard} does not exist for the customer. */
        NOEXIST,
        /**
         * Generic failure occurred. */
        FAILURE,
        /**
         * Method ended successfully. */
        SUCCESS,
        /**
         * The {@link CreditCard} to be deleted is the customer's last, and cannot be. */
        LAST_CARD
    };

    /**
     * Removes a {@link CreditCard} from a {@link Customer}.
     *
     * @param customerId The ID of the customer whose {@link CreditCard} is being deleted.
     * @param creditCardNumber The number of the {@link CreditCard} to be deleted.
     *
     * @return A status code from {@link REMOVE_CLIENT_STATUS}.
     */
    public static REMOVE_CREDIT_CARD_STATUS removeCreditCard(int customerId, long creditCardNumber) {
        Customer customer = Theater.getCustomerList().getAccount(customerId);

        if (customer == null) {
            return REMOVE_CREDIT_CARD_STATUS.NOEXIST;
        }
        else {
            try {
                if (customer.removeCreditCard(creditCardNumber)) {
                    return REMOVE_CREDIT_CARD_STATUS.SUCCESS;
                }
                else {
                    return REMOVE_CREDIT_CARD_STATUS.FAILURE;
                }
            } catch (Customer.CustomerMinimumCreditCardsException ex) {
                return REMOVE_CREDIT_CARD_STATUS.LAST_CARD;
            }
        }
    }



    /**
     * Returned codes used from {@link Theater#addShow(int, String, Date, Date, double)}.
     */
    enum ADD_SHOW_STATUS {
        /**
         * No client with the clientId exists. */
        NOEXIST,
        /**
         * Generic failure occurred. */
        FAILURE,
        /**
         * Method completed successfully. */
        SUCCESS,
        /**
         * Another show already is happening during the given dates. */
        SHOW_CONFLICT,
        /**
         * The start date is after the end date. */
        DATE_MISMATCH
    };

    /**
     * Adds a show to the {@link ShowList}.
     *
     * @param clientId The client owning the show.
     * @param showName The name of the show.
     * @param startDate The starting Date of the show.
     * @param endDate The ending Date of the show.
     * @param ticketPrice The baseline price for admittance to the show.
     *
     * @return A code from {@link ADD_SHOW_STATUS}.
     */
    public static ADD_SHOW_STATUS addShow(int clientId, String showName, Date startDate, Date endDate, double ticketPrice) {
        Client client = Theater.getClientList().getAccount(clientId);

        if (client == null) {
            return ADD_SHOW_STATUS.NOEXIST;
        }
        else {
            try {
                Show show = new Show(client, showName, startDate, endDate, ticketPrice);
                    if (Theater.getShowList().addShow(show)) {
                        return ADD_SHOW_STATUS.SUCCESS;
                    }
                    else {
                        return ADD_SHOW_STATUS.FAILURE;
                    } 
                    
            } catch (ShowList.ShowConflictException ex) {
                    return ADD_SHOW_STATUS.SHOW_CONFLICT;
            } catch (Show.ShowDateMismatchException ex) {
                return ADD_SHOW_STATUS.DATE_MISMATCH;
            }
        }
    }



    /**
     * @return An iterable class to be used for iterating through the customer list.
     */
    public static Iterable<Show> getShows() {
        return getShowList();
    }



    /**
     * Returned codes used from {@link Theater#sellTickets(TicketType, int, int, long, Date)}.
     */
    enum SELL_TICKETS_STATUS {
        /**
         * Too few or two many (e.g. 0 or -1) tickets to sell. */
        INVALID_QUANTITY,
        /**
         * The entered show date does not correspond with a show. */
        INVALID_SHOW_DATE,
        /**
         * The entered customer ID does not correspond with a customer. */
        INVALID_CUSTOMER_ID,
        /**
         * The entered credit card number was not one of the customer's own credit cards. */
        INVALID_CREDIT_CARD_NUMBER,
        /**
         * Generic failure occurred. Currently unused. */
        FAILURE,
        /**
         * Method completed successfully. */
        SUCCESS,
    }

    /**
     * Sells a ticket to a customer.
     *
     * @param ticketType The type of ticket being sold.
     * @param quantity The number of tickets to sell.
     * @param customerId The ID of the customer buying the tickets.
     * @param creditCardNumber The card number to use for making the purchase.
     * @param showDate The date of the show for the ticket.
     *
     * @return A code from {@link SELL_TICKETS_STATUS}.
     */
    public static SELL_TICKETS_STATUS sellTickets(TicketType ticketType, int quantity, int customerId, long creditCardNumber, Date showDate) {
        if (quantity < 1) {
            return SELL_TICKETS_STATUS.INVALID_QUANTITY;
        }
        else {
            Customer customer = Theater.getCustomerList().getAccount(customerId);
            Show show = ShowList.getShow(showDate);

            if (customer == null) {
                return SELL_TICKETS_STATUS.INVALID_CUSTOMER_ID;
            }
            else if (show == null) {
                return SELL_TICKETS_STATUS.INVALID_SHOW_DATE;
            }
            else {
                CreditCard creditCard = customer.getCreditCard(creditCardNumber);

                if (creditCard == null) {
                    return SELL_TICKETS_STATUS.INVALID_CREDIT_CARD_NUMBER;
                }
                else {
                    try {
                        for (int i = 0; i < quantity; i++) {
                            TicketType.whenTicketSold(ticketType.getNewTicket(show, customer, showDate));
                        }

                        return SELL_TICKETS_STATUS.SUCCESS;
                    } catch (TicketType.TicketExpired exception) {
                        return SELL_TICKETS_STATUS.INVALID_SHOW_DATE;
                    }
                }
            }
        }
    }



    /**
     * Returned codes used from {@link Theater#payClient(int, double)}.
     */
    enum PAY_CLIENT_STATUS {
        /**
         * Generic failure occurred. Currently unused. */
        FAILURE,
        /**
         * Method completed successfully. */
        SUCCESS,
        /**
         * The client does not exist. */
        NO_CLIENT,
        /**
         * The amount to be paid is greater than the amount owed. */
        TOO_LITTLE_OWED,
    }

    /**
     * Pays a client some amount of what they are owed.
     *
     * @param clientId The ID of the client to be paid.
     * @param amountToPay The amount to pay the client.

     * @return A code from {@link PAY_CLIENT_STATUS}.
     */
    public static PAY_CLIENT_STATUS payClient(int clientId, double amountToPay) {
        Client client = Theater.getClientList().getAccount(clientId);

        if (client == null) {
            return PAY_CLIENT_STATUS.NO_CLIENT;
        }
        else {
            double owed = getOwedToClient(client.getId());

            if (owed < amountToPay) {
                return PAY_CLIENT_STATUS.TOO_LITTLE_OWED;
            }
            else {
                client.adjustBalance(amountToPay * -1);

                return PAY_CLIENT_STATUS.SUCCESS;
            }
        }
    }



    /**
     * Get the amount owed to a client.
     *
     * @param clientId The ID of the client.
     * @return The amount the client is owed. Returns 0 if client does not exist.
     */
    public static double getOwedToClient(int clientId) {
        Client client = Theater.getClientList().getAccount(clientId);

        if (client == null) {
            return 0;
        }
        else {
            return client.getBalance();
        }
    }



    /*################################
     * Client/Customer/Show/CreditCard Instances
     *###############################*/


    /**
     * @return The singleton instance of {@link ClientList}, allowing modification of the client list singleton or use of ClientList-specific functionality.
     */
    public static ClientList getClientList() {
        return ClientList.getInstance();
    }

    /**
     * @return The singleton instance of {@link CustomerList}, allowing modification of the show list or use of CustomerList-specific functionality.
     */
    public static CustomerList getCustomerList() {
        return CustomerList.getInstance();
    }

    /**
     * @return The singleton instance of {@link ShowList}, allowing modification of the show list or use of ShowList-specific functionality.
     */
    public static ShowList getShowList() {
        return ShowList.getInstance();
    }
    
    /**
     * @return The singleton instance of {@link CreditCardList}, allowing modification of the credit card list or use of CreditCardList-specific functionality.
     */
    public static CreditCardList getCreditCardList() {
        return CreditCardList.getInstance();
    }
    
    /**
     * @return The singleton instance of {@link TicketList}, allowing modification of the ticket list or use of TicketList-specific functionality.
     */
    public static TicketList getTicketList() {
        return TicketList.getInstance();
    }

}