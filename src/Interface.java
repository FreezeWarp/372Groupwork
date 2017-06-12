/**
 * Created by joseph on 12/06/17.
 */
public class Interface {
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

    static String commandMethods[] = {
        "addClient",
    };

    public static void main(String args[]) {
        for (int i = 0; i < commands.length; i++) {
            System.out.println(i + ": " + commands[i]);
        }

        long commandNumber;
        while ((commandNumber = InterfacePrompts.promptIntRange("Make a selection: ", 0, commands.length - 1)) != 0) {
            // Do stuff

            switch ((int) commandNumber) {
                case 1: addClient(); break;
                case 2: removeClient(); break;
                case 3: listClients(); break;
                case 4: addCustomer(); break;
            }
        }
    }

    /**
     * @author Joseph
     */
    public static void addClient() {
        String name = InterfacePrompts.promptLine("Client name? ");
        String address = InterfacePrompts.promptLine("Client address? ");
        long phone = InterfacePrompts.promptIntRange("Phone number? ", 0, 9999999999l);
        long creditCardNo = InterfacePrompts.promptInt("Credit card number? ");
        //int creditCardExpiration = promptInt("Credit card expiration? "); // TODO: date
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

    /**
     * @author Joseph
     */
    public static void addCustomer() {

    }
}
