package br.com.churchmanager.builder;

import br.com.churchmanager.model.Pagina;

public class PaginaBuilder {

	private Pagina pagina;

	public PaginaBuilder() {
		this.pagina = new Pagina();
	}

	public PaginaBuilder comNome(String nome) {
		this.pagina.setNome(nome);
		return this;
	}

	public PaginaBuilder comDescricao(String descricao) {
		this.pagina.setDescricao(descricao);
		return this;
	}

	public Pagina build() {
		return pagina;
	}

}
