package br.com.churchmanager.model.filter;

import java.io.Serializable;

public class AtividadeEclesiasticaFilter implements Filter, Serializable {

	private static final long serialVersionUID = 5423301463701329577L;

	private String nome;
	private String descricao;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

}