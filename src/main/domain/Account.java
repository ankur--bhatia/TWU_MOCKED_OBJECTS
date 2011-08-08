package main.domain;

public class Account {
    private AccountType accountType;
    private Money money;
    private String accountId;
    private Currency inCurrency;

    public Account(String accountId, AccountType accountType, Currency inCurrency, Money money) {
        this.accountType = accountType;
        this.inCurrency = inCurrency;
        this.money = money;
        this.accountId = accountId;
    }


    public AccountType getAccountType() {
        return accountType;
    }
}
