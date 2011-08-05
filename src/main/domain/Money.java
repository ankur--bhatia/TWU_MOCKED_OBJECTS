package main.domain;

import java.math.BigDecimal;

public class Money {
    private BigDecimal value;
    private Currency currency;

    public Money(BigDecimal value, Currency currency) {
        this.value = value;
        this.currency = currency;
    }

    public Money multiply(BigDecimal multiplier)
    {
       return new Money(value.multiply(multiplier),currency);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Money)) {
            return false;
        }

        Money money = (Money) o;

        if (currency != null ? !currency.equals(money.currency) : money.currency != null) {
            return false;
        }
        if (value != null ? !value.equals(money.value) : money.value != null) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        int result = value != null ? value.hashCode() : 0;
        result = 31 * result + (currency != null ? currency.hashCode() : 0);
        return result;
    }
}
