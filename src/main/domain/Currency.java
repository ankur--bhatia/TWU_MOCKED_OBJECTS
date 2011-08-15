package main.domain;

public class Currency {
    private String currencyCode;

    public static final Currency CAD = new Currency("CAD");
    public static final Currency USD = new Currency("USD");
    public static final Currency INR = new Currency("INR");

    public Currency(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    @Override
    public String toString() {
        return "Currency{" +
                "currencyCode='" + currencyCode + '\'' +
                '}';
    }
}
