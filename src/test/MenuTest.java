package test;

import main.BankSystem;
import main.Menu;
import main.UserInterface.UserInterface;
import main.domain.Currency;
import main.domain.Money;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static org.mockito.Mockito.*;

public class MenuTest {

    private Menu menu;
    private BankSystem bankSystem;
    private UserInterface userInterface;
    private final String fromAccount = "123";
    private final String toAccount = "567";
    private final String amount = "50";
    private final String currency = "CAD";

    @Before
    public void setUp()
    {
        bankSystem = mock(BankSystem.class);
        userInterface = mock(UserInterface.class);
        menu = new Menu(bankSystem,userInterface);
    }

    @Test
    public void testMainMenu() throws Exception {
        when(userInterface.getOptionNumber()).thenReturn(0);
        menu.mainMenu();
        verify(userInterface).print("1 - Transfer Funds");
        verify(userInterface).print("0 - Exit");
    }

    @Test
    public void testTransferMenu() throws Exception {

        when(userInterface.getOptionNumber()).thenReturn(1);
        when(userInterface.getUserInput()).thenReturn(fromAccount, toAccount, amount, currency);

        menu.mainMenu();

        verify(userInterface).print("From Account Number: ");
        verify(userInterface).print("To Account Number: ");
        verify(userInterface).print("Amount Value: ");
        verify(userInterface).print("Amount Currency: ");
        verify(bankSystem).transfer(fromAccount,toAccount,new Money(new BigDecimal(50), Currency.CAD));
    }




}
