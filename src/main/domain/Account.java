package main.domain;

public class Account {
    private AccountType accountType;
    private Money money;
    private String accountId;

    public Currency getInCurrency() {
        return inCurrency;
    }

    private Currency inCurrency;

    public Account(String accountId, AccountType accountType, Currency inCurrency, Money money) {
        if( ! inCurrency.equals(money.getCurrency()))
            throw new RuntimeException("Cannot create account with balance in different currency");
        this.accountType = accountType;
        this.inCurrency = inCurrency;
        this.money = money;
        this.accountId = accountId;
    }


    public boolean isConversionRequiredForTransactionIn(Currency currency)
    {
           if(this.inCurrency.equals(currency))
               return false;
        return true;
    }



    public AccountType getAccountType() {
        return accountType;
    }

}
