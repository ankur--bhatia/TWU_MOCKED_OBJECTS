package test;

import main.AccountManager.AccountService;
import main.AccountManager.AccountServiceFactory;
import main.AccountManager.InsufficientFundsException;
import main.BankSystem;
import main.domain.Account;
import main.domain.AccountType;
import main.domain.Currency;
import main.domain.Money;
import org.junit.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class BankSystemTest {

    @MockitoAnnotations.Mock
    private AccountService accountServiceMockForFromAccount;

    @MockitoAnnotations.Mock
    private AccountService accountServiceMockForToAccount;

    @MockitoAnnotations.Mock
    private AccountServiceFactory accountServiceFactoryMock;

    private BankSystem bankSystem;
    private Account toAccount;
    private Account fromAccount;

    @Before
    public void setUp()
    {
       MockitoAnnotations.initMocks(this);
       toAccount = new Account("1234", AccountType.SAVINGS, Currency.INR,new Money(new BigDecimal(50),Currency.INR));
       fromAccount = new Account("4567", AccountType.SAVINGS, Currency.INR, new Money(new BigDecimal(40),Currency.INR));
       bankSystem = new BankSystem(accountServiceFactoryMock);
    }


    @Test
    public void ShouldTransferTheAmountBetweenTwoAccountsOfSameCurrency() throws InsufficientFundsException {
        Money amountToTransfer = new Money(new BigDecimal(30), Currency.INR);

        when(accountServiceFactoryMock.Create(toAccount)).thenReturn(accountServiceMockForToAccount);
        when(accountServiceFactoryMock.Create(fromAccount)).thenReturn(accountServiceMockForFromAccount);

        bankSystem.transfer(fromAccount, toAccount,amountToTransfer);
        verify(accountServiceMockForToAccount).deposit(amountToTransfer);
        verify(accountServiceMockForFromAccount).withdraw(amountToTransfer);
    }


}
