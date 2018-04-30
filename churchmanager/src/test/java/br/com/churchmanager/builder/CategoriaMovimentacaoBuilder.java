package br.com.churchmanager.builder;

import br.com.churchmanager.model.CategoriaMovimentacao;
import br.com.churchmanager.model.Status;

public class CategoriaMovimentacaoBuilder {

	private CategoriaMovimentacao categoria;

	public CategoriaMovimentacaoBuilder() {
		this.categoria = new CategoriaMovimentacao();
	}

	public CategoriaMovimentacaoBuilder comDescricao(String descricao) {
		this.categoria.setDescricao(descricao);
		return this;
	}

	public CategoriaMovimentacaoBuilder comId(Long id) {
		this.categoria.setId(id);
		return this;
	}

	public CategoriaMovimentacaoBuilder comNome(String nome) {
		this.categoria.setNome(nome);
		return this;
	}

	public CategoriaMovimentacaoBuilder comStatus(Status status) {
		this.categoria.setStatus(status);
		return this;
	}

	public CategoriaMovimentacaoBuilder ativo() {
		this.categoria.setStatus(Status.ATIVO);
		return this;
	}

	public CategoriaMovimentacaoBuilder inativo() {
		this.categoria.setStatus(Status.INATIVO);
		return this;
	}

	public CategoriaMovimentacao build() {
		return this.categoria;
	}

}
