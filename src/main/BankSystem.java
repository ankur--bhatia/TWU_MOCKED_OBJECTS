package main;

import main.Repositories.AccountRepository;
import main.domain.Account;
import main.domain.InsufficientFundsException;
import main.domain.Money;

public class BankSystem {


    private final AccountRepository accountRepository;

    public BankSystem(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public void transfer(String fromAccountId, String toAccountId, Money money) throws InsufficientFundsException {

        Account fromAccount = accountRepository.load(fromAccountId);
        Account toAccount = accountRepository.load(toAccountId);

        fromAccount.withdraw(money);
        toAccount.deposit(money);

        accountRepository.update(fromAccount);
        accountRepository.update(toAccount);

    }

}
