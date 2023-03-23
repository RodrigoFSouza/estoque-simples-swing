package br.com.portifolio.util;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class ConversorUtil {

    public static BigDecimal toBigDecimal(String valor) {
        try {
            return new BigDecimal(valor).setScale(4, RoundingMode.HALF_EVEN);
        } catch (Exception ex) {
            return BigDecimal.ZERO;
        }
    }
}
