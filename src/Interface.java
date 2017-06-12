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
        help();

        long commandNumber;
        while ((commandNumber = InterfacePrompts.promptIntRange("Make a selection: ", 0, commands.length - 1)) != 0) {
            // Do stuff

            switch ((int) commandNumber) {
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
                case 11: storeData(); break;
                case 12: retrieveData(); break;
                case 13: help(); break;
            }
        }

        //if (InterfacePrompts.promptYesOrNo("Would you like to save the application data before exiting? ")) {
        //    storeData();
        //}
    }


    /**
     * @author Joseph
     */
    public static void addClient() {
        String name = InterfacePrompts.promptLine("Client name? ");
        String address = InterfacePrompts.promptLine("Client address? ");
        long phone = InterfacePrompts.promptIntRange("Phone number? ", 0, 9999999999l);
    }

    /**
     * @author Joseph
     */
    public static void removeClient() {

    }

    /**
     * @author Joseph
     */
    public static void listClients() {

    }


    public static void addCustomer() {

    }

    public static void removeCustomer() {
        long creditCardNo = InterfacePrompts.promptInt("Credit card number? ");
        //int creditCardExpiration = promptInt("Credit card expiration? "); // TODO: date
    }

    public static void addCreditCard() {

    }

    public static void removeCreditCard() {

    }

    public static void listCustomers() {

    }

    public static void addShow() {

    }

    public static void listShows() {

    }

    public static void storeData() {

    }

    public static void retrieveData() {

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
