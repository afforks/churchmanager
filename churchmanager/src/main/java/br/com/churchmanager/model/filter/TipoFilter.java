package br.com.churchmanager.model.filter;

import java.io.Serializable;

public class TipoFilter implements Filter, Serializable {
	private static final long serialVersionUID = 1232701463701329577L;
	private String nome;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

}