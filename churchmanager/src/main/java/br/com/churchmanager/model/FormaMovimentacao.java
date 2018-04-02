package br.com.churchmanager.model;

public enum FormaMovimentacao {
	EM_ESPECIE("Em espécie", "background: #4CAF50"), EM_CONTA("Em conta", "background: #673AB7");

	private String descricao;
	private String cor;

	private FormaMovimentacao(String descricao, String cor) {
		this.descricao = descricao;
		this.cor = cor;
	}

	public String getDescricao() {
		return this.descricao;
	}

	public String getCor() {
		return this.cor;
	}
}