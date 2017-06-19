/**
 * A list of Accounts.
 *
 * Created by joseph on 12/06/17.
 */

/**
 * Created by joseph on 12/06/17.
 */
public class AccountList<E extends Account> extends SingletonMap<E> {
    /* Singleton Stuff */
    private static AccountList INSTANCE;

    protected AccountList() { }

    public static AccountList getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new AccountList();
        }

        return INSTANCE;
    }



    /* The List Itself */
    /**
     * Adds a new account to the AccountList.
     *
     * @param account
     */
    public void addAccount(E account) {
        addEntry(account.getId(), account);
    }

    /**
     * Removes an account from the AccountList, by its ID.
     *
     * @param accountId The accountID belonging to the account to remove.
     *
     * @return True on success, false on failure (ID doesn't exist, probably)
     */
    public boolean removeAccount(int accountId) {
        return removeEntry(accountId);
    }


    /**
     * Retries an account from the AccountList, by its ID.
     *
     * @param accountId The accountId belonging to the account to be returned
     *
     * @return True on success, false on failure (ID doesn't exist, probably)
     */
    public E getAccount(int accountId) {
        return (E) singletonMap.get(accountId);

    }

    public boolean validateAccount(int accountId) {
        return validateEntry(accountId);
    }

    /**
     * @return A new, unique account ID.
     */
    public int getNewAccountId() {
        return getNewKey();
    }
}
