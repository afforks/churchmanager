package br.com.churchmanager.model;

public enum Sexo {

	M("Masculino"), F("Feminino");

	private Sexo(String descricao) {
		this.descricao = descricao;
	}

	private String descricao;

	public String getDescricao() {
		return descricao;
	}

}