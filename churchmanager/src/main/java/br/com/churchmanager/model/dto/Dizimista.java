package br.com.churchmanager.model.dto;

import java.io.Serializable;

public class Dizimista implements Serializable {

	private static final long serialVersionUID = 286564413761286068L;

	private String nome;

	public Dizimista(String nome) {
		this.nome = nome;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

}
