package main;

import main.UserInterface.UserInterface;
import main.domain.Currency;
import main.domain.InsufficientFundsException;
import main.domain.Money;

import java.math.BigDecimal;

public class Menu {

    private BankSystem bankSystem;
    private UserInterface userInterface;

    public Menu(BankSystem bankSystem, UserInterface userInterface) {
        this.bankSystem = bankSystem;
        this.userInterface = userInterface;
    }

    public void mainMenu()  {
        boolean keepContinuing = true;
        do{
            userInterface.print("1 - Transfer Funds\n");
            userInterface.print("0 - Exit\n");
            int userOption = userInterface.getOptionNumber();

            switch (userOption)
            {
                case 1 :
                      transferMoneyMenu();
                case 0 :
                    userInterface.print("Bye");
                    keepContinuing = false;
            }
        }
        while (keepContinuing);
    }

    private void  transferMoneyMenu()  {
        userInterface.print("From Account Number: ");
        String fromAccount = userInterface.getUserInput();
        userInterface.print("To Account Number: ");
        String toAccount = userInterface.getUserInput();
        userInterface.print("Amount Value: ");
        String amountInString = userInterface.getUserInput();
        userInterface.print("Amount Currency: ");
        String currency = userInterface.getUserInput();
        Money amountToTransfer = new Money(new BigDecimal(amountInString), Currency.parse(currency));
        try
        {
        bankSystem.transfer(fromAccount,toAccount,amountToTransfer);
        }
        catch (InsufficientFundsException ex)
        {
            userInterface.print("Insufficient Funds to complete the transfer");
        }
    }


}
