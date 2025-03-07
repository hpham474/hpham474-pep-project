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
        if (!account.getUsername().equals("") &&
            account.getPassword().length() >= 4 && 
            accountDAO.getAccountByUser(account.getUsername()) == null) {
                accountDAO.insertAccount(account);
                return accountDAO.getAccountByUser(account.getUsername());
            }

        return null;
    }

    public Account loginUser(Account account) {
        Account attemptedAccount = accountDAO.getAccountByUser(account.getUsername());

        if (attemptedAccount == null) {
            return null;
        }

        if (attemptedAccount.getPassword().equals(account.getPassword())) {
            return attemptedAccount;
        }

        return null;
    }

    public Account getAccountByUserID(int id) {
        return accountDAO.getAccountByUserID(id);
    }

}