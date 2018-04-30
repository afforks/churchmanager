package br.com.churchmanager.builder;

import br.com.churchmanager.model.CategoriaMovimentacao;
import br.com.churchmanager.model.Status;

public class CategoriaMovimentacaoBuilder {

	private CategoriaMovimentacao atividade;

	public CategoriaMovimentacaoBuilder() {
		this.atividade = new CategoriaMovimentacao();
	}

	public CategoriaMovimentacaoBuilder comDescricao(String descricao) {
		this.atividade.setDescricao(descricao);
		return this;
	}

	public CategoriaMovimentacaoBuilder comId(Long id) {
		this.atividade.setId(id);
		return this;
	}

	public CategoriaMovimentacaoBuilder comNome(String nome) {
		this.atividade.setNome(nome);
		return this;
	}

	public CategoriaMovimentacaoBuilder comStatus(Status status) {
		this.atividade.setStatus(status);
		return this;
	}

	public CategoriaMovimentacaoBuilder ativo() {
		this.atividade.setStatus(Status.ATIVO);
		return this;
	}

	public CategoriaMovimentacaoBuilder inativo() {
		this.atividade.setStatus(Status.INATIVO);
		return this;
	}

	public CategoriaMovimentacao build() {
		return this.atividade;
	}

}
