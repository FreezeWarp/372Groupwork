import java.util.Date;

/**
 * Created by joseph on 05/07/17.
 */
public class TestSuite {
    public static void main(String args[]) {
        // For easier testing (feel free to add your own entries after the needed refactoring into Theater).


        if (Theater.addClient("Bobby Johnson", "123 Downing St.", 4445559100L)  != Theater.ADD_CLIENT_STATUS.SUCCESS) { // client ID = 0
            System.out.println("Add client 1 failed.");
        }
        if (Theater.addClient("Sony Pictures", "10202 West Washington Boulevard", 3108884455L) != Theater.ADD_CLIENT_STATUS.SUCCESS) { // client ID = 1
            System.out.println("Add client 2 failed.");
        }
        if (Theater.addClient("Bad Client", "Who Cares?", -1L) != Theater.ADD_CLIENT_STATUS.PHONE_NUMBER_OUT_OF_RANGE) {
            System.out.println("Add client 3 returned wrong status code.");
        }
        if (Theater.addClient("Bad Client", "Who Cares?", 1111111111111111L) != Theater.ADD_CLIENT_STATUS.PHONE_NUMBER_OUT_OF_RANGE) {
            System.out.println("Add client 4 returned wrong status code.");
        }


        if (Theater.addShow(0, "Cats, on Ice!", new Date(199, 5, 11), new Date(199, 5, 16), 10) != Theater.ADD_SHOW_STATUS.SUCCESS) {
            System.out.println("Add show 1 failed.");
        }
        if (Theater.addShow(1, "Dogs, on Grass!", new Date(199, 5, 21), new Date(199, 5, 25), 20) != Theater.ADD_SHOW_STATUS.SUCCESS) {
            System.out.println("Add show 2 failed.");
        }
        if (Theater.addShow(0, "Birds, on Planes!", new Date(199, 6, 3), new Date(199, 6, 25), 30) != Theater.ADD_SHOW_STATUS.SUCCESS) {
            System.out.println("Add show 3 failed.");
        }
        if (Theater.addShow(1, "You, in the Audience!", new Date(199, 7, 0), new Date(199, 7, 20), 40) != Theater.ADD_SHOW_STATUS.SUCCESS) {
            System.out.println("Add show 4 failed.");
        }
        if (Theater.addShow(0, "Bad show.", new Date(199, 5, 12), new Date(199, 5, 14), 40) != Theater.ADD_SHOW_STATUS.SHOW_CONFLICT) {
            System.out.println("Add show 5 returns wrong status code.");
        }
        if (Theater.addShow(0, "Bad show.", new Date(199, 6, 3), new Date(199, 6, 25), 40) != Theater.ADD_SHOW_STATUS.SHOW_CONFLICT) {
            System.out.println("Add show 6 returns wrong status code.");
        }
        if (Theater.addShow(0, "Bad show.", new Date(199, 5, 0), new Date(199, 5, 18), 40) != Theater.ADD_SHOW_STATUS.SHOW_CONFLICT) {
            System.out.println("Add show 7 returns wrong status code.");
        }
        if (Theater.addShow(0, "Bad show.", new Date(199, 5, 0), new Date(199, 5, 30), 40) != Theater.ADD_SHOW_STATUS.SHOW_CONFLICT) {
            System.out.println("Add show 8 returns wrong status code.");
        }


        try {
            if (Theater.addCustomer("Homer Simpson", "742 Evergreen Terrace", 5552324499L, new CreditCard(9999999999999999L, new Date(200, 11, 0))) != Theater.ADD_CUSTOMER_STATUS.SUCCESS) { // customer ID = 0
                System.out.println("Add customer 1 failed.");
            }
            if (Theater.addCreditCard(0, new CreditCard(8888888888888888L, new Date(190, 5, 0))) != Theater.ADD_CREDIT_CARD_STATUS.SUCCESS) {
                System.out.println("Add creditcard 1 to customer 1 failed.");
            }
        } catch (Exception ex) {
            System.out.println("Erroneous exception caught.");
        }


        if (Theater.sellTickets(TicketType.StudentAdvanceTicket, 3, 0, 9999999999999999L, new Date(199, 5, 15)) != Theater.SELL_TICKETS_STATUS.SUCCESS) {
            System.out.println("Sell ticket 1 failed.");
        }
        if (Theater.sellTickets(TicketType.Ticket, 3, 0, 8888888888888888L, new Date(199, 5, 25)) != Theater.SELL_TICKETS_STATUS.SUCCESS) {
            System.out.println("Sell ticket 2 failed.");
        }
        if (Theater.sellTickets(TicketType.Ticket, 3, 0, 7777777777777777L, new Date(199, 6, 20)) != Theater.SELL_TICKETS_STATUS.INVALID_CREDIT_CARD_NUMBER) {
            System.out.println("Sell ticket 3 returns wrong status code.");
        }
        if (Theater.sellTickets(TicketType.Ticket, 3, 0, 8888888888888888L, new Date(199, 8, 0)) != Theater.SELL_TICKETS_STATUS.INVALID_SHOW_DATE) {
            System.out.println("Sell ticket 4 returns wrong status code.");
        }



        if (UserInterfacePrompts.promptYesOrNo("Would you like to run the main userinterface now? ")) {
            UserInterface.main(args);
        }
    }
}
