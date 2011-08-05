package test.domain;

import main.domain.Currency;
import main.domain.Money;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;

public class MoneyTest {
    private Money money;

    @Test
    public void testEquals()
    {
        money = new Money(new BigDecimal(23), Currency.CAD);
        Assert.assertFalse(money.equals(null));
        Assert.assertFalse(money.equals(new Object()));
        Assert.assertFalse(money.equals(new Money(new BigDecimal(45), Currency.CAD)));
        Assert.assertTrue(money.equals(new Money(new BigDecimal(23),Currency.CAD)));
    }

    @Test
    public void testMultiply()
    {
        Money money = new Money(new BigDecimal(12),Currency.CAD);
        Money expectedProduct = new Money(new BigDecimal(24),Currency.CAD);
        Money product = money.multiply(new BigDecimal(2));
        Assert.assertEquals(expectedProduct,product);
    }

}
