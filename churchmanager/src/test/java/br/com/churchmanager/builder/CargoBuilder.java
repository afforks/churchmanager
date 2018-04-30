package br.com.churchmanager.builder;

import br.com.churchmanager.model.Cargo;
import br.com.churchmanager.model.Status;

public class CargoBuilder {

	private Cargo cargo;

	public CargoBuilder() {
		this.cargo = new Cargo();
	}

	public CargoBuilder comDescricao(String descricao) {
		this.cargo.setDescricao(descricao);
		return this;
	}

	public CargoBuilder comId(Long id) {
		this.cargo.setId(id);
		return this;
	}

	public CargoBuilder comNome(String nome) {
		this.cargo.setNome(nome);
		return this;
	}

	public CargoBuilder comStatus(Status status) {
		this.cargo.setStatus(status);
		return this;
	}

	public CargoBuilder ativo() {
		this.cargo.setStatus(Status.ATIVO);
		return this;
	}

	public CargoBuilder inativo() {
		this.cargo.setStatus(Status.INATIVO);
		return this;
	}

	public Cargo build() {
		return this.cargo;
	}

}
