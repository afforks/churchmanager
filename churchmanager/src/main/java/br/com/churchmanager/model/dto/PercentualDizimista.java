package br.com.churchmanager.model.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.DecimalFormat;

public class PercentualDizimista implements Serializable {
	private static final long serialVersionUID = -7566679881358277939L;
	private int mes;
	private int ano;
	private BigDecimal porcentagem;

	public PercentualDizimista(int mes, int ano, BigDecimal porcentagem) {
		this.mes = mes;
		this.ano = ano;
		this.porcentagem = porcentagem;
	}

	public int getMes() {
		return this.mes;
	}

	public void setMes(int mes) {
		this.mes = mes;
	}

	public int getAno() {
		return this.ano;
	}

	public void setAno(int ano) {
		this.ano = ano;
	}

	public BigDecimal getPorcentagem() {
		return this.porcentagem;
	}

	public String formatarPorcentagem() {
		DecimalFormat df = new DecimalFormat("###.#");
		return df.format(porcentagem.doubleValue());
	}

	public void setPorcentagem(BigDecimal porcentagem) {
		this.porcentagem = porcentagem;
	}
}