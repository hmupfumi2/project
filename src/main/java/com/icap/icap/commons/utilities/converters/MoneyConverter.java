package com.icap.icap.commons.utilities.converters;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;
import java.util.Locale;

/**
 * @author Wilson Chiviti
 * @created 12/02/2021 - 22:02
 * @project novapay-transactions-engine
 */

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class MoneyConverter {

    public static String formatToMoney(BigDecimal value) {
        return formatToMoney(value.setScale(2, RoundingMode.HALF_EVEN).doubleValue());
    }

    public static String formatToMoney(Long value) {
        return formatToMoney(value.doubleValue());
    }

    public static String formatToMoney(double value) {
        try {
            NumberFormat formatCurrency = NumberFormat.getCurrencyInstance(Locale.ENGLISH);
            String amount = formatCurrency.format(value);
            char[] charSequence = amount.toCharArray();
            int index = 0;
            char[] var8 = charSequence;
            int var9 = charSequence.length;

            for (int var10 = 0; var10 < var9; ++var10) {
                char ch = var8[var10];
                if (Character.isDigit(ch)) {
                    break;
                }

                ++index;
            }

            return amount.substring(index);
        } catch (Exception var12) {
            var12.printStackTrace();
        }
        return null;
    }

}
