package br.com.churchmanager.util;

import java.math.BigDecimal;
import java.text.NumberFormat;

public class MonetarioUtil {
	private static NumberFormat numberFormat;

	public static String formatarReal(BigDecimal valor) {
		numberFormat = NumberFormat.getCurrencyInstance();
		return numberFormat.format(valor);
	}

	public static String formatarReal(double valor) {
		numberFormat = NumberFormat.getCurrencyInstance();
		return numberFormat.format(valor);
	}

	public static String formatarReal(Number valor) {
		numberFormat = NumberFormat.getCurrencyInstance();
		return numberFormat.format(valor);
	}
}