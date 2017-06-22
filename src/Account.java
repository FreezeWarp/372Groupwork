import java.io.Serializable;

/**
 * A generic account for individuals (or organisations) with a name, address, and phone number.
 *
 * @author  Joseph Parsons
 * @version 1.0
 * @since   2017-06-22
 */
public class Account extends IdentifiableInteger implements Serializable {
    /**
     * The name of the account holder.
     */
    private String name;

    /**
     * The address of the account holder.
     */
    private String address;

    /**
     * The phone number of the account holder.
     */
    private long phoneNumber;

    /**
     * The minimum valid phone number.
     */
    private static final long PHONE_NUMBER_MINIMUM = 1L;

    /**
     * The maximum valid phone number.
     */
    private static final long PHONE_NUMBER_MAXIMUM = 99999999999L;


    /**
    * @param name the name of the account holder
    * @param address the address of the account holder
    * @param phoneNumber the phone number of the account holder
    */
    public Account(String name, String address, long phoneNumber) throws AccountPhoneNumberOutOfRangeException {
        this.name = name;
        this.address = address;
        setPhoneNumber(phoneNumber);
    }


    /**
    * Gets the name associated with an account
    * 
    * @return a string representation of the account holder's name
    */
    public String getName() {
        return name;
    }

    /**
    * Gets the address associated with an account
    * 
    * @return a string representation of the account holder's address
    */
    public String getAddress() {
        return address;
    }

    /**
    * Gets the phone number associated with an account
    * 
    * @return a long representation of the account holder's phone number
    */
    public long getPhoneNumber() {
        return phoneNumber;
    }


    /**
     * @param phoneNumber The account holder's phone number, {@link Account#phoneNumber}
     * @throws AccountPhoneNumberOutOfRangeException When the phone number is not within the valid phone number range.
     */
    private void setPhoneNumber(long phoneNumber) throws AccountPhoneNumberOutOfRangeException {
        if (phoneNumber < PHONE_NUMBER_MINIMUM
                || phoneNumber > PHONE_NUMBER_MAXIMUM) {
            throw new AccountPhoneNumberOutOfRangeException();
        }
        else {
            this.phoneNumber = phoneNumber;
        }
    }

    /**
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
