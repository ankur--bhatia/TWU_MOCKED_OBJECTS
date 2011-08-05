package main.AccountManager;

import main.domain.Account;

public class AccountServiceFactory {
    public  AccountService Create(Account account)
    {
        switch ( account.getAccountType())
        {
             case SAVINGS:
                 return new SavingsAccountService(account);
             case SWEEP:
                 return new SweepAccountService(account);
             default:
                 throw new RuntimeException("Not a known savings account");

        }

    }

}
