package br.com.churchmanager.model.group;

import java.io.Serializable;

public class MovimentacaoAnual implements Serializable {
	private static final long serialVersionUID = 9036990226888130460L;
	private String tipo;
	private String status;
	private String data;
	private double valor;

	public MovimentacaoAnual() {
	}

	public MovimentacaoAnual(String tipo, String status, String data, double valor) {
		this.tipo = tipo;
		this.status = status;
		this.data = data;
		this.valor = valor;
	}

	public String getTipo() {
		return this.tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getData() {
		return this.data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public double getValor() {
		return this.valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}
}