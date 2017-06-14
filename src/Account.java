import java.io.Serializable;

/**
 * A generic financial account.
 *
 * Created by joseph on 12/06/17.
 */
public class Account implements Serializable {
    private int id;
    private String name;
    private String address;
    private long phoneNumber;
    private int balance = 0;

    public Account(int id, String name, String address, long phoneNumber) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public long getPhoneNumber() {
        return phoneNumber;
    }

    public int getBalance() { //right now this is being shared with Customer() class as well, even though it doesn't use it. Should we delete this maybe and move it to Client() class?
        return balance;
    }

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

    public int adjustBalance(int balanceAdjustment) {
        return this.balance += balanceAdjustment;
    }
}
