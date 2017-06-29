import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

/**
 * A collection of prompts to obtain user input.
 *
 * @author  Eric Fulwiler, Daniel Johnson, Joseph Parsons, and Cory Stadther
 * @version 1.0
 * @since   2017-06-22
 */
public class UserInterfacePrompts {
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
            else if (inputInt > 18) {
                System.out.println("That number is too high. Please enter a number between " + min + " and " + 18);
            }
            else if (inputInt == 16 || inputInt==17)
                System.out.println("They aren't added yet, also delete this before we submit"); //delete this later
            else {
                break;
            }
        }

        return inputInt;
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
     * Prompts the user for a string of text matching certain parameters.
     *
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
     * Prompts the user for a yes or no answer.
     *
     * @param promptText The text to display for the prompt.
     *
     * @return True if the user answered yes; false otherwise.
     */
    public static boolean promptYesOrNo(String promptText) {
        return promptLine(promptText).substring(0, 1).toLowerCase().equals("y");
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
                return Long.parseLong(promptLineRegex(promptText, "[\\s\\W]+", "^[0-9]{10,11}$", "That is not a valid phone number. Please enter a phone number, optionally containing an international calling code. For instance, enter 123-456-7890 or 1-123-456-7890."));
            } catch (Exception ex) {
                System.out.println("That phone number could not be parsed. This may reflect an internal error, but you probably just typed something really strange. Try typing something more phone-numbery.");
            }
        }
    }


    /**
     * Prompts the user for a credit card expiry date in the format mmyyyy. Checks
     * to see if the expiry date entered is a valid date.
     *
     * @param promptText The text to display for the prompt.
     *
     * @return A string containing the expiry date of the credit card
     */
    public static Date promptCreditCardExpiry(String promptText) {
        while(true) {
            try {
                String expiryStr = (promptLineRegex(promptText, "[\\s\\W]+", "^(0?[1-9]|10|11|12)[0-9]{4}$", "That is not a valid expiry date. Please enter the 6 digit expiry date in the format MMyyyy.")); //removes unneeded characters

                return new SimpleDateFormat("MMyy").parse(expiryStr);
            } catch (Exception ex) {
                System.out.println("That credit card expiry date could not be parsed. This may reflect an internal error, but you probably just typed something really strange. Please try re-entering the expiry date in the format MMyyyy.");
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
    public static long promptCreditCardNumber(String promptText) {
        while(true) {
            try {
                return Long.parseLong(promptLineRegex(promptText, "[\\s\\W]+", "^[0-9]{16}$", "That is not a valid credit card number. Please enter a 16-digit credit card number."));
            } catch (Exception ex) {
                System.out.println("That credit card number could not be parsed. This may reflect an internal error, but you probably just typed something really strange. Try typing something more credit card-numbery.");
            }
        }
    }


    /**
     * Prompts the user to enter information for a credit card, including the number and expiration date
     *
     * @param promptTextForNumber the text to display for the credit card number prompt
     * @param promptTextForExpiry the text to display for the credit card expiration date prompt
     *
     * @return A credit card object containing a number and expiration date
     */
    public static CreditCard promptCreditCard(String promptTextForNumber, String promptTextForExpiry) {
        long creditCardNo = UserInterfacePrompts.promptCreditCardNumber(promptTextForNumber);
        Date expiryDate = UserInterfacePrompts.promptCreditCardExpiry(promptTextForExpiry);

        while (true) {
            try {
                return new CreditCard(creditCardNo, expiryDate);
            } catch (CreditCard.CreditCardExpiredException ex) {
                System.out.println("This card is expired, please enter in a new credit card.");
                creditCardNo = UserInterfacePrompts.promptCreditCardNumber(promptTextForNumber);
                expiryDate = UserInterfacePrompts.promptCreditCardExpiry(promptTextForExpiry);
            } catch (CreditCard.CreditCardOutOfRangeException ex) {
                System.out.println("The credit card is out-of-range. This may be an internal error.");
                return null;
            }
        }
    }


    /**
     * Prompts the user for a date to start or end a show.
     *
     * @param promptText The text to display for the prompt.
     *
     * @return Date the user input
     */
    public static Date promptShowDate(String promptText) {
        while(true) {
            try {
                String dateStr = (promptLineRegex(promptText, "[\\s\\W]+", "^[0-9]{8}$", //removes unneeded characters
                        "That is not a show date. Please enter the 8 digit expiry date in the format MM/dd/yyyy."));

                Date myDate = new SimpleDateFormat("MMddyyyy").parse(dateStr);

                boolean passed = myDate.before(new Date()); //compares the date of the show with the current date
                if (passed) {
                    System.out.println("This day has already passed."); // TODO: move/copy validation into Show class
                } else {
                    return myDate;
                }

            } catch (Exception ex) {
                System.out.println("Did you type something incorrectly? Please try re-entering the expiry show date in the format MMDDyyyy.");
            }
        }

    }
}
