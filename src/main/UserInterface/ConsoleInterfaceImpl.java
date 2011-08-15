package main.UserInterface;

public class ConsoleInterfaceImpl implements UserInterface{
    public void print(String value) {
        System.out.print(value);
    }

    public String getUserInput() {
        String inputLine = System.console().readLine();
        return inputLine;
    }

    public int getOptionNumber() {
        String inputLine = System.console().readLine();
        return Integer.getInteger(inputLine);
    }
}
