package com.ijala.util;

import java.text.DecimalFormat;

public class Formater {
    private static final DecimalFormat DECIMAL_FORMAT = new DecimalFormat("#,##0.00");

    public static String formatarValor(double value) {
        return DECIMAL_FORMAT.format(value);
    }
}