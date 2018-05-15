package br.com.churchmanager.util.custom;

import java.math.BigDecimal;

public class MyNumberCustom extends Number {
	private static final long serialVersionUID = -8561003808209128699L;
	private BigDecimal number;

	public MyNumberCustom(String number) {
		this.number = new BigDecimal(number);
	}

	public int intValue() {
		return this.number.intValue();
	}

	public long longValue() {
		return this.number.longValue();
	}

	public float floatValue() {
		return this.number.floatValue();
	}

	public double doubleValue() {
		return this.doubleValue();
	}
}