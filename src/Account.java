import java.io.Serializable;

/**
 * A generic financial account.
 *
 * Created by Joseph T. Parsons on 12/06/17.
 */
public class Account extends IdentifiableInteger implements Serializable {
    private String name;
    private String address;
    private long phoneNumber;

    /**
    * Constructor for the Account class
    * 
    * @param name the name of the account holder
    * @param address the address of the account holder
    * @param phoneNumber the phone number of the account holder
    * 
    * @return nothing
    */
    public Account(String name, String address, long phoneNumber) {
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }

    /**
    * Gets the name associated with an account
    * 
    * @param nothing
    * 
    * @return a string representation of the account holder's name
    */
    public String getName() {
        return name;
    }

    /**
    * Gets the address associated with an account
    * 
    * @param nothing
    * 
    * @return a string representation of the account holder's address
    */
    public String getAddress() {
        return address;
    }

    /**
    * Gets the phone number associated with an account
    * 
    * @param nothing
    * 
    * @return a long representation of the account holder's phone number
    */
    public long getPhoneNumber() {
        return phoneNumber;
    }

    /**
    * Overrides the toString method of Object
    * 
    * @param nothing
    * 
    * @return a string representation concatenating basic account information
    */
    @Override
    public String toString() {
        StringBuilder phoneNumberString = new StringBuilder(Long.toString(phoneNumber));
        phoneNumberString.insert(7, '-');
        phoneNumberString.insert(4, '-');
        if (phoneNumberString.length() > 13)
            phoneNumberString.insert(1, '-');

        return id +
                ": " + name +
                ", " + address +
                ", " + phoneNumber;
    }   
}
