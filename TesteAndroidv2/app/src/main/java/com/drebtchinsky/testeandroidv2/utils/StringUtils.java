package com.drebtchinsky.testeandroidv2.utils;

import org.jetbrains.annotations.NotNull;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;

public class StringUtils {

    public static final String LANGUAGE = "pt";
    public static final String COUNTRY = "br";
    public static final String DEFAULT_FORMAT = "R$";
    public static final String IDEAL_FORMAT = "R$ ";

    public static String currencyFormat(double value) {
        NumberFormat format = getNumberFormat();
        return format.format(value).replace(DEFAULT_FORMAT, IDEAL_FORMAT);
    }

    @NotNull
    private static NumberFormat getNumberFormat() {
        return DecimalFormat.getCurrencyInstance(new Locale(LANGUAGE, COUNTRY));
    }

    public static String agencyFormat(String value) {
        return value.replaceAll("(\\d{2})(\\d{6})(\\d)", "$1.$2-$3");
    }
}
