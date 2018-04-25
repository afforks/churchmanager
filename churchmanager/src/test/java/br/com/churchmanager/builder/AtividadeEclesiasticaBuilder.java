package br.com.churchmanager.builder;

import br.com.churchmanager.model.AtividadeEclesiastica;
import br.com.churchmanager.model.Status;

public class AtividadeEclesiasticaBuilder {

	private AtividadeEclesiastica atividade;

	public AtividadeEclesiasticaBuilder() {
		this.atividade = new AtividadeEclesiastica();
	}

	public AtividadeEclesiasticaBuilder comDescricao(String descricao) {
		this.atividade.setDescricao(descricao);
		return this;
	}

	public AtividadeEclesiasticaBuilder comId(Long id) {
		this.atividade.setId(id);
		return this;
	}

	public AtividadeEclesiasticaBuilder comNome(String nome) {
		this.atividade.setNome(nome);
		return this;
	}

	public AtividadeEclesiasticaBuilder comStatus(Status status) {
		this.atividade.setStatus(status);
		return this;
	}

	public AtividadeEclesiasticaBuilder ativo() {
		this.atividade.setStatus(Status.ATIVO);
		return this;
	}

	public AtividadeEclesiasticaBuilder inativo() {
		this.atividade.setStatus(Status.INATIVO);
		return this;
	}

	public AtividadeEclesiastica build() {
		return this.atividade;
	}

}
