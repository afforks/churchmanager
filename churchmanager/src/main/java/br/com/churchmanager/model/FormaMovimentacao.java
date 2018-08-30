package br.com.churchmanager.model;

public enum FormaMovimentacao {

	EM_ESPECIE("Em espécie", "background: #4CAF50"), EM_CONTA("Em conta", "background: #673AB7");

	private FormaMovimentacao(String descricao, String cor) {
		this.descricao = descricao;
		this.cor = cor;
	}

	private String descricao;
	private String cor;

	public String getDescricao() {
		return descricao;
	}

	public String getCor() {
		return cor;
	}

}