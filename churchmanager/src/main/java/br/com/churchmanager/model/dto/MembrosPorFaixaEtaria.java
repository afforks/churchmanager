package br.com.churchmanager.model.dto;

import java.io.Serializable;
import java.math.BigDecimal;

public class MembrosPorFaixaEtaria implements Serializable {
	private static final long serialVersionUID = 1971281763718907915L;
	private String faixaEtaria;
	private BigDecimal m;
	private BigDecimal f;

	public MembrosPorFaixaEtaria(String faixaEtaria, BigDecimal m, BigDecimal f) {
		this.faixaEtaria = faixaEtaria;
		this.m = m;
		this.f = f;
	}

	public String getFaixaEtaria() {
		return this.faixaEtaria;
	}

	public void setFaixaEtaria(String faixaEtaria) {
		this.faixaEtaria = faixaEtaria;
	}

	public BigDecimal getM() {
		return this.m;
	}

	public void setM(BigDecimal m) {
		this.m = m;
	}

	public BigDecimal getF() {
		return this.f;
	}

	public void setF(BigDecimal f) {
		this.f = f;
	}

	public String toString() {
		return "MembrosPorFaixaEtaria [faixaEtaria=" + this.faixaEtaria + ", m=" + this.m + ", f=" + this.f + "]\n";
	}
}