package test;

import main.AccountManager.AccountService;
import main.AccountManager.AccountServiceFactory;
import main.BankSystem;
import main.ForeignExchangeManager.IForeignExchangeService;
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

    @MockitoAnnotations.Mock
    private IForeignExchangeService foreignExchangeService;


    private BankSystem bankSystem;
    private Account toAccount;
    private Account fromAccount;
    private BigDecimal conversionRateFromCADToINR;
    private BigDecimal conversionRateFromINRToCAD;

    @Before
    public void setUp()
    {
       MockitoAnnotations.initMocks(this);
       bankSystem = new BankSystem(accountServiceFactoryMock, foreignExchangeService);

       conversionRateFromCADToINR = new BigDecimal(45);
       conversionRateFromINRToCAD = new BigDecimal(0.022);
    }


    @Test
    public void ShouldTransferTheAmountBetweenTwoAccountsOfSameCurrency() throws Exception {
        Money amountToTransfer = new Money(new BigDecimal(30), Currency.INR);
        toAccount = new Account("1234", AccountType.SAVINGS, Currency.INR,new Money(new BigDecimal(50),Currency.INR));
        fromAccount = new Account("4567", AccountType.SAVINGS, Currency.INR, new Money(new BigDecimal(40),Currency.INR));

        when(accountServiceFactoryMock.Create(toAccount)).thenReturn(accountServiceMockForToAccount);
        when(accountServiceFactoryMock.Create(fromAccount)).thenReturn(accountServiceMockForFromAccount);

        bankSystem.transfer(fromAccount, toAccount,amountToTransfer);
        verify(accountServiceMockForToAccount).deposit(amountToTransfer);
        verify(accountServiceMockForFromAccount).withdraw(amountToTransfer);
    }

    @Test
    public void ShouldDepositTheConvertedAmountWhenTransferBetweenTwoAccountsOfDifferentCurrency() throws Exception {
       Money amountToTransfer = new Money(new BigDecimal(30),Currency.CAD);
       Money convertedAmountToTransfer = amountToTransfer.convert(conversionRateFromCADToINR,Currency.INR);
       toAccount = new Account("1234", AccountType.SAVINGS, Currency.INR,new Money(new BigDecimal(50),Currency.INR));
       fromAccount = new Account("4567", AccountType.SAVINGS, Currency.CAD, new Money(new BigDecimal(40),Currency.CAD));

       when(accountServiceFactoryMock.Create(toAccount)).thenReturn(accountServiceMockForToAccount);
       when(accountServiceFactoryMock.Create(fromAccount)).thenReturn(accountServiceMockForFromAccount);
       when(foreignExchangeService.conversionRate(Currency.CAD, Currency.INR)).thenReturn(conversionRateFromCADToINR);

       bankSystem.transfer(fromAccount,toAccount,amountToTransfer);
       verify(accountServiceMockForToAccount).deposit(convertedAmountToTransfer);
       verify(accountServiceMockForFromAccount).withdraw(amountToTransfer);
    }

    @Test
    public void ShouldWithdrawTheConvertedAmountWhenTransferBetweenTwoAccountOfDifferentCurrency() throws Exception {
       Money amountToTransfer = new Money(new BigDecimal(450),Currency.INR);
       Money convertedAmountToWithdraw = amountToTransfer.convert(conversionRateFromINRToCAD,Currency.CAD);
       toAccount = new Account("1234", AccountType.SAVINGS, Currency.INR,new Money(new BigDecimal(50),Currency.INR));
       fromAccount = new Account("4567", AccountType.SAVINGS, Currency.CAD, new Money(new BigDecimal(40),Currency.CAD));

       when(accountServiceFactoryMock.Create(toAccount)).thenReturn(accountServiceMockForToAccount);
       when(accountServiceFactoryMock.Create(fromAccount)).thenReturn(accountServiceMockForFromAccount);
       when(foreignExchangeService.conversionRate(Currency.INR, Currency.CAD)).thenReturn(conversionRateFromINRToCAD);

       bankSystem.transfer(fromAccount,toAccount,amountToTransfer);
       verify(accountServiceMockForToAccount).deposit(amountToTransfer);
       verify(accountServiceMockForFromAccount).withdraw(convertedAmountToWithdraw);
    }




}
