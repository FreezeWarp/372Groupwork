import java.util.Scanner;

/**
 * Created by joseph on 12/06/17.
 */
public class InterfacePrompts {
    /**
     * Prompts the user for an integer.
     *
     * @param promptText The text to display for the prompt.
     *
     * @return An integer value input by the user.
     */
    public static int promptInt(String promptText) {
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


    /**
     * Prompts the user for an integer that must fall between min and max
     *
     * @param promptText The text to display for the prompt.
     * @param min The minimum allowed integer.
     * @param max The maximum allowed integer.
     *
     * @return An integer value input by the user.
     */
    public static int promptIntRange(String promptText, int min, int max) {
        int inputInt;

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


    /**
     * Prompts the user for a phone number.
     *
     * @param promptText The text to display for the prompt.
     *
     * @return A long value input by the user.
     */
    public static long promptPhone(String promptText) {
        while(true) {
            try {
                return Long.parseLong(promptLineRegex(promptText, "[\\s\\W]+", "^[0-9]{10,12}$", "That is not a valid phone number. Please enter a phone number, optionally containing an international calling code. For instance, enter 123-456-7890 or 1-123-456-7890."));
            } catch (Exception ex) {
                System.out.println("That phone number could not be parsed. This may reflect an internal error, but you probably just typed something really strange. Try typing something more phone-numbery.");
            }
        }
    }


    /**
     * Prompts the user for a credit card number.
     *
     * @param promptText The text to display for the prompt.
     *
     * @return A long value input by the user.
     */
    public static long promptCreditCard(String promptText) {
        while(true) {
            try {
                return Long.parseLong(promptLineRegex(promptText, "[\\s\\W]+", "^[0-9]{16}$", "That is not a valid credit card number. Please enter a 16-digit credit card number."));
            } catch (Exception ex) {
                System.out.println("That credit card number could not be parsed. This may reflect an internal error, but you probably just typed something really strange. Try typing something more credit card-numbery.");
            }
        }
    }


    /**
     * Prompts the user for a string of text matching certain parameters.
     * @param promptText The text to display for the prompt.
     * @param regexToFilterOut A regex that will be removed from the user's input text.
     * @param regexToMatch A regex that the user's text must match, after regexToFilterOut has been applied.
     * @param failText Text to display when regexToMatch fails.
     *
     * @return A string that matches regexToMatch and doesn't contain regexToFilterOut.
     */
    public static String promptLineRegex(String promptText, String regexToFilterOut, String regexToMatch, String failText) {
        Scanner s  = new Scanner(System.in);

        while (true) {
            System.out.print(promptText);
            String input = s.nextLine();

            // Typically used to remove space and punctuation characters from the input, since we can reasonably assume they were typed in error.
            input = input.replaceAll(regexToFilterOut, "");

            if (input.matches(regexToMatch)) {
                return input;
            }
            else {
                System.out.println(failText);
            }
        }
    }


    /**
     * Prompts the user for a line of text.
     *
     * @param promptText The text to display for the prompt.
     *
     * @return A string of text, up to (but not including) a new line.
     */
    public static String promptLine(String promptText) {
        Scanner s = new Scanner(System.in);

        System.out.print(promptText);

        String input = s.nextLine();

        return input;
    }


    /**
     * Prompts the user for a yes or no answer.
     *
     * @param promptText The text to display for the prompt.
     *
     * @return True if the user answered yes; false otherwise.
     */
    public static boolean promptYesOrNo(String promptText) {
        return promptLine(promptText).substring(0, 1).toLowerCase().equals("y");
    }
}
