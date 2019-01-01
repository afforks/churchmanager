package br.com.churchmanager.model.dto;

import java.io.Serializable;

public class MovimentacaoCategoria implements Serializable {
	private static final long serialVersionUID = 4330968348942286141L;
	private String nome;
	private double valorParcela;
	private int mes;
	private int ano;
	private String tipo;

	public MovimentacaoCategoria(String nome, double valorParcela, int mes, int ano, String tipo) {
		this.nome = nome;
		this.valorParcela = valorParcela;
		this.mes = mes;
		this.ano = ano;
		this.tipo = tipo;
	}

	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public double getValorParcela() {
		return this.valorParcela;
	}

	public void setValorParcela(double valorParcela) {
		this.valorParcela = valorParcela;
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

	public String getTipo() {
		return this.tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String toString() {
		return this.nome + ": R$ " + this.valorParcela;
	}
}