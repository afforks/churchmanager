package br.com.churchmanager.builder;

import java.math.BigDecimal;

import br.com.churchmanager.model.Movimentacao;

public class MovimentacaoBuilder {

	private Movimentacao movimentacao;

	public MovimentacaoBuilder() {
		this.movimentacao = new Movimentacao();
	}

	public MovimentacaoBuilder comValor(float valor) {
		this.movimentacao.setValor(new BigDecimal(valor));
		return this;
	}

	public MovimentacaoBuilder comValor(BigDecimal valor) {
		this.movimentacao.setValor(valor);
		return this;
	}

	public MovimentacaoBuilder comParcelas(int numeroParcelas) {
		this.movimentacao.setNumeroParcelas(numeroParcelas);
		return this;
	}

	public Movimentacao build() {
		return this.movimentacao;
	}

}
