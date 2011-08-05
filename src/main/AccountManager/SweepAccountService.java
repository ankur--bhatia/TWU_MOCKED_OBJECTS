package main.AccountManager;

import main.domain.Account;
import main.domain.Currency;
import main.domain.Money;

public class SweepAccountService extends AccountService{

    protected SweepAccountService(Account account) {
        super(account);
    }

    public Currency currency() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public void deposit(Money amount) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public void withdraw(Money amount) throws InsufficientFundsException {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
