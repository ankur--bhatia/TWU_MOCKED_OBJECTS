package main;

import main.AccountManager.AccountService;
import main.AccountManager.AccountServiceFactory;
import main.AccountManager.InsufficientFundsException;
import main.domain.Account;
import main.domain.Money;

public class BankSystem {

   private AccountServiceFactory accountServiceFactory;

    public BankSystem(AccountServiceFactory accountServiceFactory) {
        this.accountServiceFactory = accountServiceFactory;
    }

    public void transfer(Account from,Account to, Money money) throws InsufficientFundsException {
       AccountService accountServiceForFromAccount = accountServiceFactory.Create(from);
       AccountService accountServiceForToAccount = accountServiceFactory.Create(to);
       accountServiceForFromAccount.withdraw(money);
       accountServiceForToAccount.deposit(money);
   }



}
