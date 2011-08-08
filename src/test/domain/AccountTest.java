package test.domain;

import junit.framework.Assert;
import main.domain.Account;
import main.domain.AccountType;
import main.domain.Currency;
import main.domain.Money;
import org.junit.Test;
import org.junit.Before;

import java.math.BigDecimal;

public class AccountTest {
    private Account account;

    @Before
    public void setUp()
    {
       account = new Account("232424", AccountType.SAVINGS, Currency.INR, new Money(new BigDecimal(23), Currency.CAD));
    }


    @Test
    public void testGetAccountType() throws Exception {

        Assert.assertEquals(account.getAccountType(),AccountType.SAVINGS);
    }

    @Test
    public void testIsConversionRequiredForTransactionIn()
    {
        Assert.assertEquals(true,account.isConversionRequiredForTransactionIn(Currency.INR));
    }

}
