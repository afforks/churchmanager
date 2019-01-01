package br.com.churchmanager.model.filter;

import java.io.Serializable;

import br.com.churchmanager.model.Sexo;

public class PessoaFilter implements Filter, Serializable {

	private static final long serialVersionUID = 5043742463701329577L;

	private String nome;
	private String mes;
	private String ano;
	private Sexo sexo;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getMes() {
		return mes;
	}

	public void setMes(String mes) {
		this.mes = mes;
	}

	public String getAno() {
		return ano;
	}

	public void setAno(String ano) {
		this.ano = ano;
	}

	public Sexo getSexo() {
		return sexo;
	}

	public void setSexo(Sexo sexo) {
		this.sexo = sexo;
	}

}