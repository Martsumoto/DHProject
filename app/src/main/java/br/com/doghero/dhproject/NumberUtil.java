package br.com.doghero.dhproject;

import java.math.BigDecimal;
import java.text.NumberFormat;

public class NumberUtil {

    private static final String BRAZILIAN_REAL_CURRENCY_SYMBOL = "R$";
    private static final String UNKNOWN_VALUE = "?";

    public static String formatNumberToCurrencyText(BigDecimal value) {
        String textValue;
        NumberFormat format = NumberFormat.getIntegerInstance();

        if (value != null) {
            textValue = BRAZILIAN_REAL_CURRENCY_SYMBOL + format.format(value);
        } else {
            textValue = UNKNOWN_VALUE;
        }

        return textValue;
    }

}
