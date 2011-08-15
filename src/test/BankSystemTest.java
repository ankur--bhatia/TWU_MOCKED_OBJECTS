package test;

import junit.framework.Assert;
import main.BankSystem;
import main.Repositories.AccountRepository;
import main.domain.Account;
import main.domain.Currency;
import main.domain.Money;
import org.junit.Test;
import org.junit.Before;


import java.math.BigDecimal;

import static org.mockito.Mockito.*;

public class BankSystemTest {

    private BankSystem bankSystem;
    private AccountRepository accountRepository;
    private final String toAccountId = "123";
    private final String fromAccountId = "567";
    private Account toAccount;
    private Account fromAccount;
    private Money amountToTransfer;

    @Before
    public void setUp()
    {
        toAccount = new Account(toAccountId, Currency.CAD,new Money(new BigDecimal(100),Currency.CAD));
        fromAccount = new Account(fromAccountId,Currency.CAD,new Money(new BigDecimal(50),Currency.CAD));
        accountRepository = mock(AccountRepository.class);
        bankSystem = new BankSystem(accountRepository);
    }

    @Test
    public void testTransfer() throws Exception {

        when(accountRepository.load(toAccountId)).thenReturn(toAccount);
        when(accountRepository.load(fromAccountId)).thenReturn(fromAccount);
        amountToTransfer  = new Money(new BigDecimal(25),Currency.CAD);

        bankSystem.transfer(fromAccountId,toAccountId,amountToTransfer);

        verify(accountRepository).load(toAccountId);
        verify(accountRepository).load(fromAccountId);
        verify(accountRepository).update(toAccount);
        verify(accountRepository).update(fromAccount);
        Money expectedBalanceInFromAccount = new Money(new BigDecimal(25), Currency.CAD);
        Money expectedBalanceInToAccount = new Money(new BigDecimal(125),Currency.CAD);
        Assert.assertEquals(expectedBalanceInToAccount,toAccount.getBalance());
        Assert.assertEquals(expectedBalanceInFromAccount,fromAccount.getBalance());
    }
}
