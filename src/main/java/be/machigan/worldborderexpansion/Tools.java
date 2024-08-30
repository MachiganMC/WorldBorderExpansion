package be.machigan.worldborderexpansion;

import java.math.BigDecimal;
import java.text.DecimalFormat;

public class Tools {

    public static final DecimalFormat DECIMAL_FORMAT = new DecimalFormat("##.##");
    public static final DecimalFormat BIG_DOUBLE_FORMAT = new DecimalFormat("#.#");

    static {
        BIG_DOUBLE_FORMAT.setMaximumFractionDigits(10);
        BIG_DOUBLE_FORMAT.setGroupingUsed(false);
    }

    public static double round(double n, int digit) {
        double multiplier = Math.pow(10, digit);
        return Math.round(n * multiplier) / multiplier;
    }

    public static double round(double n) {
        return round(n, 2);
    }

    public static String betterNumber(int n) {
        if (n == 0) {
            return Integer.toString(0);
        }
        boolean belowZero = false;
        StringBuilder sb = new StringBuilder();
        if (n < 0) {
            belowZero = true;
            n *= -1;
        }

        int i = 0;
        while (n > 0) {
            if (i % 3 == 0 && i != 0) {
                sb.append(" ").append(n%10);
            } else {
                sb.append(n%10);
            }
            i++;
            n /= 10;
        }
        sb.reverse();
        return (belowZero ? "-" + sb : sb.toString());
    }

    public static String betterNumber(double d) {
        d = Tools.round(d);
        if (((int) d) == d)
            return betterNumber((int) d);
        String[] array = new BigDecimal(d).toPlainString().split("\\.");
        return betterNumber(Integer.parseInt(array[0])) +  (array[1].equalsIgnoreCase("0") ? "" : "." + array[1]);
    }

    public static String shortNumber(double n) {
        n = Tools.round(n);
        boolean belowZero = n < 0;
        n = Math.abs(n);
        if (n < 500) {
            return (belowZero ? "-" : "") + ((int) n == n ? Integer.toString((int) n) : Double.toString(n));
        }
        if (n >= 500 && n <= 500000) {
            return (belowZero ? "-" : "") + DECIMAL_FORMAT.format(Math.floor((n / 1_000) * 100) / 100) + "k";
        }
        if (n > 500000) {
            return (belowZero ? "-" : "") + DECIMAL_FORMAT.format(Math.floor((n / 1_000_000) * 100) / 100) + "M";
        }
        return (belowZero ? "-" : "") + DECIMAL_FORMAT.format(Math.floor(n * 100) / 100);
    }
}
