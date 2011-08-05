package test.AccountManager;

import junit.framework.Assert;
import main.AccountManager.AccountServiceFactory;
import main.AccountManager.SavingsAccountService;
import main.AccountManager.SweepAccountService;
import main.domain.Account;
import main.domain.AccountType;
import main.domain.Money;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

public class AccountServiceFactoryTest {

     private AccountServiceFactory accountServiceFactory;
     private Account account;
     private Money accountBalance;

    @Before
    public void setUp()
    {
        accountServiceFactory = new AccountServiceFactory();
        accountBalance =  new Money(new BigDecimal(23),main.domain.Currency.CAD);

    }


    @Test
    public void testShouldCreateSavingsAccountServiceForSavingsAccountType()
    {
        account = new Account("232425", AccountType.SAVINGS, accountBalance );
        Assert.assertEquals(SavingsAccountService.class,accountServiceFactory.Create(account).getClass());
    }

    @Test
    public void testShouldCreateSweepInAccountServiceForSavingsAccountType()
    {
        account = new Account("2424525", AccountType.SWEEP, accountBalance );
        Assert.assertEquals(SweepAccountService.class,accountServiceFactory.Create(account).getClass());
    }


}
