package br.com.churchmanager.builder;

import java.math.BigDecimal;

import br.com.churchmanager.model.Dizimo;

public class DizimoBuilder {

	private Dizimo dizimo;
	
	public DizimoBuilder() {
		this.dizimo = new Dizimo();
	}
	
	public DizimoBuilder comValorOferta(double valorOferta) {
		this.dizimo.setValorOferta(new BigDecimal(valorOferta));
		return this;
	}
	
	public DizimoBuilder comValorOferta(BigDecimal valorOferta) {
		this.dizimo.setValorOferta(valorOferta);
		return this;
	}

	public DizimoBuilder comValorDizimo(BigDecimal valorDizimo) {
		this.dizimo.setValorDizimo(valorDizimo);
		return this;
	}
	
	public DizimoBuilder comValorDizimo(double valorDizimo) {
		this.dizimo.setValorDizimo(new BigDecimal(valorDizimo));
		return this;
	}
	
	public Dizimo build() {
		return this.dizimo;
	}

}
