package br.com.churchmanager.model.filter;

import java.io.Serializable;

public class EventoFilter implements Filter, Serializable {

	private static final long serialVersionUID = 5043712329577L;

	private String nome;
	private String dia;
	private String mes;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDia() {
		return dia;
	}

	public void setDia(String dia) {
		this.dia = dia;
	}

	public String getMes() {
		return mes;
	}

	public void setMes(String mes) {
		this.mes = mes;
	}

}