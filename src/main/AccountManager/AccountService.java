package main.AccountManager;

import main.domain.Account;
import main.domain.Currency;
import main.domain.Money;


public abstract class AccountService {

    private Account account;

    abstract Currency currency();

	abstract void deposit(Money amount);

	abstract void withdraw(Money amount) throws InsufficientFundsException;

    protected AccountService(Account account) {
        this.account = account;
    }
}
