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

    public static void main(String args[]) {
        for (int i = 0; i < commands.length; i++) {
            System.out.println(i + ": " + commands[i]);
        }

        int commandNumber;
        while ((commandNumber = promptInt("Make a selection: ", 0, commands.length - 1)) != 0) {
            // Do stuff
        }
    }

    public static int promptInt(String promptText, int min, int max) {
        Scanner s  = new Scanner(System.in);
        int inputInt = 0;

        System.out.println(promptText);

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
}
