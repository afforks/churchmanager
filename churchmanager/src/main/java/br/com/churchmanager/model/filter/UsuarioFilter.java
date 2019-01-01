package br.com.churchmanager.model.filter;

import java.io.Serializable;

public class UsuarioFilter implements Filter, Serializable {

	private static final long serialVersionUID = 5043701463701329577L;

	private String nome;
	private String nomeIdentificador;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getNomeIdentificador() {
		return nomeIdentificador;
	}

	public void setNomeIdentificador(String nomeIdentificador) {
		this.nomeIdentificador = nomeIdentificador;
	}

}