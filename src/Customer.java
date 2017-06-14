import java.util.ArrayList;

/**
 * A Theater Customer's Account.
 *
 * Created by joseph on 12/06/17.
 */
public class Customer extends Account {
    private ArrayList<CreditCard> creditCardList = new ArrayList<CreditCard>();

    public Customer(String name, String address, long phoneNumber, CreditCard creditCard) {
        super(Theater.getInstance().getCustomerList().getNewAccountId(), name, address, phoneNumber);
        creditCardList.add(creditCard);
      
    }
    
    public void addCreditCard(CreditCard creditCard) {
    	creditCardList.add(creditCard);
    }
    
    public CreditCard getCreditCard() {
    	return creditCardList.get(0);
    }
    
    public CreditCard getCreditCard(int index) {
    	return creditCardList.get(index);
    }
    
    public ArrayList<CreditCard> getCreditCardList() {
    	return creditCardList;
    }
    
    @Override
    public String toString() {
    	String creditCardString = "";
        for (int index = 0; index < creditCardList.size(); index++) {
        	creditCardString += index + ": " + creditCardList.get(index);
        }
       return super.toString() + creditCardString;
    }
    
}