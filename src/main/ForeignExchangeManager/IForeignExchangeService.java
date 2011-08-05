package main.ForeignExchangeManager;

import main.domain.Currency;

import java.math.BigDecimal;

public interface IForeignExchangeService {

	BigDecimal conversionRate(Currency from, Currency to);
}
