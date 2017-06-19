/**
 * A list of Accounts.
 *
 * Created by joseph on 12/06/17.
 */
public class AccountList<E extends Account> extends SingletonMap<Integer, E> {
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
     * @param account The account object to add to the AccountList.
     *
     * @return True on success, false on failure (typically, an entry with the same account ID already exists; this should not happen as long as getNewAccountId() is used when creating the Account object).
     */
    public boolean addAccount(E account) {
        return addEntry(account);
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
        return hasEntry(accountId);
    }
}
