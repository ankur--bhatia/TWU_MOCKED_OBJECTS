package main.domain;

public class Account {
    private AccountType accountType;
    private Money money;
    private String accountId;

    public Account(String accountId, AccountType accountType, Money money) {
        this.accountType = accountType;
        this.money = money;
        this.accountId = accountId;
    }


    public AccountType getAccountType() {
        return accountType;
    }
}
