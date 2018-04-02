package br.com.churchmanager.model.group;

import java.io.Serializable;

public class Totais implements Serializable {
	private static final long serialVersionUID = 8254322008411984850L;
	private int mes;
	private int ano;
	private double recebidas;
	private double aReceber;
	private double pagas;
	private double aPagar;

	public Totais(int mes, int ano, double recebidas, double aReceber, double pagas, double aPagar) {
		this.mes = mes;
		this.ano = ano;
		this.recebidas = recebidas;
		this.aReceber = aReceber;
		this.pagas = pagas;
		this.aPagar = aPagar;
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

	public double getRecebidas() {
		return this.recebidas;
	}

	public void setRecebidas(double recebidas) {
		this.recebidas = recebidas;
	}

	public double getaReceber() {
		return this.aReceber;
	}

	public void setaReceber(double aReceber) {
		this.aReceber = aReceber;
	}

	public double getPagas() {
		return this.pagas;
	}

	public void setPagas(double pagas) {
		this.pagas = pagas;
	}

	public double getaPagar() {
		return this.aPagar;
	}

	public void setaPagar(double aPagar) {
		this.aPagar = aPagar;
	}
}