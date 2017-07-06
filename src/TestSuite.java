import java.util.Date;

/**
 * Created by joseph on 05/07/17.
 */
public class TestSuite {
    public static void main(String args[]) {
        // For easier testing (feel free to add your own entries after the needed refactoring into Theater).


        /* Add Client Tests */
        TestSuite.equalsTest(Theater.addClient("Bobby Johnson", "123 Downing St.", 4445559100L), Theater.ADD_CLIENT_STATUS.SUCCESS, "Add client 1 failed."); // client ID = 0
        TestSuite.equalsTest(Theater.addClient("Sony Pictures", "10202 West Washington Boulevard", 3108884455L), Theater.ADD_CLIENT_STATUS.SUCCESS, "Add client 2 failed."); // client ID = 1
        TestSuite.equalsTest(Theater.addClient("Bad Client", "Who Cares?", -1L), Theater.ADD_CLIENT_STATUS.PHONE_NUMBER_OUT_OF_RANGE, "Add client 3 returned wrong status code.");
        TestSuite.equalsTest(Theater.addClient("Bad Client", "Who Cares?", 1111111111111111L), Theater.ADD_CLIENT_STATUS.PHONE_NUMBER_OUT_OF_RANGE, "Add client 4 returned wrong status code.");


        /* Add Show Tests */
        TestSuite.equalsTest(Theater.addShow(0, "Cats, on Ice!", new Date(199, 5, 11), new Date(199, 5, 16), 10), Theater.ADD_SHOW_STATUS.SUCCESS, "Add show 1 failed."); // show ID = 0
        TestSuite.equalsTest(Theater.addShow(1, "Dogs, on Grass!", new Date(199, 5, 21), new Date(199, 5, 25), 20), Theater.ADD_SHOW_STATUS.SUCCESS, "Add show 2 failed."); // show ID = 1
        TestSuite.equalsTest(Theater.addShow(0, "Birds, on Planes!", new Date(199, 6, 3), new Date(199, 6, 25), 30), Theater.ADD_SHOW_STATUS.SUCCESS, "Add show 3 failed."); // show ID = 2
        TestSuite.equalsTest(Theater.addShow(1, "You, in the Audience!", new Date(199, 7, 0), new Date(199, 7, 20), 40), Theater.ADD_SHOW_STATUS.SUCCESS, "Add show 4 failed."); // show ID = 3
        TestSuite.equalsTest(Theater.addShow(0, "Bad show.", new Date(199, 5, 12), new Date(199, 5, 14), 40), Theater.ADD_SHOW_STATUS.SHOW_CONFLICT, "Add show 5 returns wrong status code.");
        TestSuite.equalsTest(Theater.addShow(0, "Bad show.", new Date(199, 6, 3), new Date(199, 6, 25), 40), Theater.ADD_SHOW_STATUS.SHOW_CONFLICT, "Add show 6 returns wrong status code.");
        TestSuite.equalsTest(Theater.addShow(0, "Bad show.", new Date(199, 5, 0), new Date(199, 5, 18), 40), Theater.ADD_SHOW_STATUS.SHOW_CONFLICT, "Add show 7 returns wrong status code.");
        TestSuite.equalsTest(Theater.addShow(0, "Bad show.", new Date(199, 5, 0), new Date(199, 5, 30), 40), Theater.ADD_SHOW_STATUS.SHOW_CONFLICT, "Add show 8 returns wrong status code.");


        /* Add Customer & Credit Card Tests */
        try {
            TestSuite.equalsTest(Theater.addCustomer("Homer Simpson", "742 Evergreen Terrace", 5552324499L, new CreditCard(9999999999999999L, new Date(200, 11, 0))), Theater.ADD_CUSTOMER_STATUS.SUCCESS, "Add customer 1 failed."); // customer ID = 0
            TestSuite.equalsTest(Theater.addCustomer("Marge Simpson", "742 Evergreen Terrace", 5552324499L, new CreditCard(9898989898989898L, new Date(200, 11, 0))), Theater.ADD_CUSTOMER_STATUS.SUCCESS, "Add customer 2 failed."); // customer ID = 1
            TestSuite.equalsTest(Theater.addCreditCard(0, new CreditCard(8888888888888888L, new Date(190, 5, 0))), Theater.ADD_CREDIT_CARD_STATUS.SUCCESS, "Add creditcard 1 to customer 1 failed.");
            TestSuite.equalsTest(Theater.addCreditCard(0, new CreditCard(7878787878787878L, new Date(190, 5, 0))), Theater.ADD_CREDIT_CARD_STATUS.SUCCESS, "Add creditcard 2 to customer 1 failed.");
            TestSuite.equalsTest(Theater.addCreditCard(1, new CreditCard(5555555555555555L, new Date(190, 5, 0))), Theater.ADD_CREDIT_CARD_STATUS.SUCCESS, "Add creditcard 1 to customer 2 failed.");
            TestSuite.equalsTest(Theater.addCreditCard(1, new CreditCard(5555555555555555L, new Date(190, 5, 0))), Theater.ADD_CREDIT_CARD_STATUS.CREDIT_CARD_DUPLICATE, "Add duplicate creditcard to customer 2 returns wrong status code..");
        } catch (Exception ex) {
            System.out.println("Erroneous exception caught.");
        }

        try {
            new CreditCard(88888888888888888L, new Date(190, 5, 0));
            System.out.println("Exception failed to fire on bad credit card number.");
        } catch (Exception ex) {
        }

        try {
            new CreditCard(8888888888888888L, new Date(100, 5, 0));
            System.out.println("Exception failed to fire on bad credit card expiration.");
        } catch (Exception ex) {
        }


        /* Sell Ticket Tests */
        TestSuite.equalsTest(Theater.sellTickets(TicketType.StudentAdvanceTicket, 3, 0, 9999999999999999L, new Date(199, 5, 15)), Theater.SELL_TICKETS_STATUS.SUCCESS, "Sell ticket 1 failed.");
        TestSuite.equalsTest(Theater.sellTickets(TicketType.Ticket, 3, 0, 8888888888888888L, new Date(199, 5, 25)), Theater.SELL_TICKETS_STATUS.SUCCESS, "Sell ticket 2 failed.");
        TestSuite.equalsTest(Theater.sellTickets(TicketType.Ticket, 3, 0, 7777777777777777L, new Date(199, 6, 20)), Theater.SELL_TICKETS_STATUS.INVALID_CREDIT_CARD_NUMBER, "Sell ticket 3 returns wrong status code.");
        TestSuite.equalsTest(Theater.sellTickets(TicketType.Ticket, 3, 0, 8888888888888888L, new Date(199, 8, 0)), Theater.SELL_TICKETS_STATUS.INVALID_SHOW_DATE, "Sell ticket 4 returns wrong status code.");



        if (UserInterfacePrompts.promptYesOrNo("Would you like to run the main userinterface now? ")) {
            UserInterface.main(args);
        }
    }

    public static void equalsTest(Object returnValue, Object shouldReturnValue, String failureMessage) {
        if (returnValue != shouldReturnValue) {
            System.out.println(failureMessage);
        }
    }
}
