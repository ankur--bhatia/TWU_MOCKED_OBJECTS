package test.domain;

import junit.framework.Assert;
import main.domain.Account;
import main.domain.AccountType;
import main.domain.Currency;
import main.domain.Money;
import org.junit.Test;

import java.math.BigDecimal;

public class AccountTest {
    private Account account;

    @Test
    public void testGetAccountType() throws Exception {
        account = new Account("232424", AccountType.SAVINGS, Currency.INR, new Money(new BigDecimal(23), Currency.CAD));
        Assert.assertEquals(account.getAccountType(),AccountType.SAVINGS);
    }
}
