import java.util.Scanner;

/**
 * Created by joseph on 12/06/17.
 */
public class InterfacePrompts {
    public static long promptInt(String promptText) {
        Scanner s  = new Scanner(System.in);
        int inputInt = 0;

        System.out.print(promptText);

        while (true) {
            String input = s.nextLine();

            // Remove space and punctuation characters from the input; we can reasonably assume they were typed in error. (Alphabetic characters will still go through, and likely cause an exception next.)
            input = input.replace("[\\s\\W]+", "");

            try {
                inputInt = Integer.parseInt(input);
                break;
            } catch (Exception ex) {
                System.out.println("That is not a number. Please enter a number.");
            }
        }

        return inputInt;
    }

    public static long promptIntRange(String promptText, long min, long max) {
        long inputInt;

        while (true) {
            inputInt = promptInt(promptText);

            if (inputInt < min) {
                System.out.println("That number is too low. Please enter a number between " + min + " and " + max);
            }
            else if (inputInt > max) {
                System.out.println("That number is too high. Please enter a number between " + min + " and " + max);
            }
            else {
                break;
            }
        }

        return inputInt;
    }

    public static String promptLine(String promptText) {
        Scanner s = new Scanner(System.in);

        System.out.print(promptText);

        String input = s.nextLine();

        return input;
    }
}
