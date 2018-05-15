package br.com.churchmanager.builder;

import java.util.Arrays;

import br.com.churchmanager.model.Pagina;
import br.com.churchmanager.model.Perfil;
import br.com.churchmanager.model.Status;

public class PerfilBuilder {

	private Perfil perfil;

	public PerfilBuilder() {
		this.perfil = new Perfil();
	}

	public PerfilBuilder comDescricao(String descricao) {
		this.perfil.setDescricao(descricao);
		return this;
	}

	public PerfilBuilder comNome(String nome) {
		this.perfil.setNome(nome);
		return this;
	}

	public PerfilBuilder comPaginas(Pagina... paginas) {
		this.perfil.setPaginas(Arrays.asList(paginas));
		return this;
	}

	public PerfilBuilder comStatus(Status status) {
		this.perfil.setStatus(status);
		return this;
	}

	public PerfilBuilder ativo() {
		this.perfil.setStatus(Status.ATIVO);
		return this;
	}

	public PerfilBuilder inativo() {
		this.perfil.setStatus(Status.INATIVO);
		return this;
	}

	public Perfil builder() {
		return this.perfil;
	}
}
