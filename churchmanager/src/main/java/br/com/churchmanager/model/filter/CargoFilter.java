package br.com.churchmanager.model.filter;

import java.io.Serializable;

public class CargoFilter implements Filter, Serializable {

	private static final long serialVersionUID = 11043712329577L;

	private String nome;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

}