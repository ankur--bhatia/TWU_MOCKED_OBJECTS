package main.AccountManager;

import main.domain.Account;
import main.domain.Currency;
import main.domain.Money;


public abstract class AccountService {

    private Account account;

    public abstract Currency currency();

	public abstract void deposit(Money amount);

	public abstract void withdraw(Money amount) throws InsufficientFundsException;

    protected AccountService(Account account) {
        this.account = account;
    }
}
