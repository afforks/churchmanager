package br.com.churchmanager.model;

public enum Situacao {

	QUITADO("Quitado", "background: #49afcd"), 
	NAO_QUITADO("Não quitado", "background: #da4f49");

	private Situacao(String descricao, String cor) {
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