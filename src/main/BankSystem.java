package main;

import main.AccountManager.AccountService;
import main.AccountManager.AccountServiceFactory;
import main.AccountManager.InsufficientFundsException;
import main.ForeignExchangeManager.IForeignExchangeService;
import main.domain.Account;
import main.domain.Money;

import java.math.BigDecimal;

public class BankSystem {

    private final AccountServiceFactory accountServiceFactory;
    private final IForeignExchangeService foreignExchangeService;

    public BankSystem(AccountServiceFactory accountServiceFactory, IForeignExchangeService foreignExchangeService) {
        this.accountServiceFactory = accountServiceFactory;
        this.foreignExchangeService = foreignExchangeService;
    }

    public void transfer(Account from, Account to, Money money) throws InsufficientFundsException {
        AccountService accountServiceForFromAccount = accountServiceFactory.Create(from);
        AccountService accountServiceForToAccount = accountServiceFactory.Create(to);

        Money amountToDeposit = money;
        Money amountToWithdraw = money;

        if (from.isConversionRequiredForTransactionIn(money.getCurrency())) {
            BigDecimal conversionRate = foreignExchangeService.conversionRate(money.getCurrency(), from.getInCurrency());
            amountToWithdraw = money.convert(conversionRate, from.getInCurrency());
        }

        if (to.isConversionRequiredForTransactionIn(money.getCurrency())) {
            BigDecimal conversionRate = foreignExchangeService.conversionRate(money.getCurrency(), to.getInCurrency());
            amountToDeposit = money.convert(conversionRate, to.getInCurrency());
        }

        accountServiceForFromAccount.withdraw(amountToWithdraw);
        accountServiceForToAccount.deposit(amountToDeposit);
    }
}
