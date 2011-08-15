package main;

import main.Repositories.AccountRepositoryImpl;
import main.UserInterface.ConsoleInterfaceImpl;

public class Program {


    public static void main(String args[])
    {
        Menu menu = new Menu(new BankSystem(new AccountRepositoryImpl()), new ConsoleInterfaceImpl());
        menu.mainMenu();
    }
}
