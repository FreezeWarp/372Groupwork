/**
 * A list of {@link Account}s and associated functionality, complying with {@link SingletonMap}.
 *
 * @author  Joseph Parsons
 * @version 2.0
 * @since   2017-06-29
 */
public abstract class AccountList<E extends Account> extends SingletonIdentifiableMap<Integer, E> {
    /*################################
     * Alter List Member Functionality
     *###############################*/

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
     * Retrieves an account from the AccountList, by its ID.
     *
     * @param accountId The accountId belonging to the account to be returned
     * 
     * @returns True on success, false on failure (ID doesn't exist, probably)
     */
    public E getAccount(int accountId) {
        return singletonMap.get(accountId);
    }


    /**
     * Checks to see if an account exists, given an accountId
     *
     * @param accountId The accountId belonging to the account to be checked
     *
     * @return True if it exists, false if it does not exist
     */
    public boolean validateAccount(int accountId) {
        return hasEntry(accountId);
    }
}
