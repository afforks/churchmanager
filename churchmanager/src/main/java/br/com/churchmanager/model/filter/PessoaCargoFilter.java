package br.com.churchmanager.model.filter;

import java.io.Serializable;

public class PessoaCargoFilter implements Filter, Serializable {

	private static final long serialVersionUID = 5043712329577L;

	private String nomePessoa;
	private String nomeCargo;

	public String getNomePessoa() {
		return nomePessoa;
	}

	public void setNomePessoa(String nomePessoa) {
		this.nomePessoa = nomePessoa;
	}

	public String getNomeCargo() {
		return nomeCargo;
	}

	public void setNomeCargo(String nomeCargo) {
		this.nomeCargo = nomeCargo;
	}

}