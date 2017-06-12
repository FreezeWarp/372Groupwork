import java.util.Scanner;

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

        int commandNumber;
        while ((commandNumber = promptInt("Make a selection: ", 0, commands.length - 1)) != 0) {
            // Do stuff

            switch (commandNumber) {
                case 1: addClient(); break;
                case 2: removeClient(); break;
                case 3: listClients(); break;
                case 4: addCustomer(); break;
            }
        }
    }

    public static void addClient() {
        String name = promptLine("Client name? ");
        String address = promptLine("Client address? ");
        //int phone = promptInt("Phone number? ", 0, 9999999999); // TODO: allow string input
        //int creditCardNo = promptInt("Credit card number? "); // TODO: allow string input
        //int creditCardExpiration = promptInt("Credit card expiration? "); // TODO: allow string input
    }

    public static void removeClient() {

    }

    public static void listClients() {

    }

    public static void addCustomer() {

    }

    public static int promptInt(String promptText, int min, int max) {
        Scanner s  = new Scanner(System.in);
        int inputInt = 0;

        System.out.print(promptText);

        while (true) {
            String input = s.next();

            try {
                inputInt = Integer.parseInt(input);

                if (inputInt < min) {
                    System.out.println("That number is too low. Please enter a number between " + min + " and " + max);
                }
                else if (inputInt > max) {
                    System.out.println("That number is too high. Please enter a number between " + min + " and " + max);
                }
                else {
                    break;
                }
            } catch (Exception ex) {
                System.out.println("That is not a number. Please enter a number.");
            }
        }

        return inputInt;
    }

    public static String promptLine(String promptText) {
        Scanner s  = new Scanner(System.in);

        System.out.print(promptText);

        String input = s.nextLine();

        return input;
    }
}
