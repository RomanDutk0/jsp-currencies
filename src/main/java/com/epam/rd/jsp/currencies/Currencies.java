package com.epam.rd.jsp.currencies;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.AbstractMap;
import java.util.Collection;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class Currencies {
    private Map<String, BigDecimal> curs = new TreeMap<>();
    private Map<String, BigDecimal> exchangeRates = new TreeMap<>();

    public void addCurrency(String currency, BigDecimal weight) {
        curs.put(currency, weight);
    }

    public Collection<String> getCurrencies() {
        return curs.keySet();
    }

    public Map<String, BigDecimal> getExchangeRates(String referenceCurrency) {
        BigDecimal currentCurs = curs.get(referenceCurrency);

        for (String name : curs.keySet())
            exchangeRates.put(name, currentCurs.divide(curs.get(name), 20, RoundingMode.HALF_UP));

        return exchangeRates.entrySet()
                .stream()
                .map(entry -> new AbstractMap.SimpleEntry<>(entry.getKey(),
                        entry.getValue().setScale(5, RoundingMode.HALF_UP)))
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (e1, e2) -> e1,
                        TreeMap::new));
    }

    public BigDecimal convert(BigDecimal amount, String sourceCurrency, String targetCurrency) {
        BigDecimal sourceRate = exchangeRates.get(sourceCurrency);
        BigDecimal targetRate = exchangeRates.get(targetCurrency);
        return amount
                .multiply(targetRate)
                .divide(sourceRate, 5, RoundingMode.HALF_UP);
    }

    public BigDecimal convert(String amount, String sourceCurrency, String targetCurrency) {
        this.getExchangeRates(sourceCurrency);
        return convert(new BigDecimal(amount), sourceCurrency, targetCurrency);
    }

}
