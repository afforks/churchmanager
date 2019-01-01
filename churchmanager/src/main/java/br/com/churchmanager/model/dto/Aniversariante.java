package br.com.churchmanager.model.dto;

import java.io.Serializable;
import java.util.Date;

public class Aniversariante implements Serializable {

	private static final long serialVersionUID = -4924708331162801772L;

	private String nome;
	private String apelido;
	private Date data;
	private int idade;

	public Aniversariante(String nome, String apelido, Date data, int idade) {
		this.nome = nome;
		this.apelido = apelido;
		this.data = data;
		this.idade = idade;
	}

	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getApelido() {
		return this.apelido;
	}

	public void setApelido(String apelido) {
		this.apelido = apelido;
	}

	public Date getData() {
		return this.data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public int getIdade() {
		return this.idade;
	}

	public void setIdade(int idade) {
		this.idade = idade;
	}
}