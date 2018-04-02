package br.com.churchmanager.model.group;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;

public class PessoaAtividaEclesiastica implements Serializable {
	private static final long serialVersionUID = 3026082101675912254L;
	private String categoria;
	private BigDecimal quantidade;

	public PessoaAtividaEclesiastica(String categoria, BigDecimal quantidade) {
		this.categoria = categoria;
		this.quantidade = quantidade;
	}

	public String getCategoria() {
		return this.categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public BigDecimal getQuantidade() {
		return this.quantidade;
	}

	public void setQuantidade(BigDecimal quantidade) {
		this.quantidade = quantidade;
	}
}