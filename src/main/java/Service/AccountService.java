package Service;

import Model.Account;
import DAO.AccountDAO;

public class AccountService {
    private AccountDAO accountDAO;

    public AccountService() {
        accountDAO = new AccountDAO();
    }

    public AccountService(AccountDAO accountDAO) {
        this.accountDAO = accountDAO;
    }

    public Account registerUser(Account account) {
        if (account.getUsername() != "" &&
            account.getPassword().length() >= 4 && 
            accountDAO.getAccountByUser(account.getUsername()) == null) {
                return accountDAO.insertAccount(account);
            }

        return null;
    }

    public Account loginUser(Account account) {
        if (accountDAO.getAccountByUser(account.getUsername()).getPassword() == account.getPassword()) {
            return account;
        }

        return null;
    }

}