package br.com.churchmanager.model;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import br.com.churchmanager.model.EntidadeGenerica;
import br.com.churchmanager.model.Pagina;

@Entity(name = "perfil")
@Table(name = "perfil")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Perfil extends EntidadeGenerica {
	private static final long serialVersionUID = 8683670150324870932L;
	@Column(name = "nome", nullable = false, unique = true)
	private String nome;
	@Column(name = "descricao")
	private String descricao;
	@ManyToMany(fetch = FetchType.EAGER)
	@Fetch(FetchMode.SUBSELECT)
	@JoinTable(name = "perfil_pagina", joinColumns = { @JoinColumn(name = "perfil_id") }, inverseJoinColumns = {
			@JoinColumn(name = "pagina_id") })
	@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
	private List<Pagina> paginas;

	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return this.descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public List<Pagina> getPaginas() {
		return this.paginas;
	}

	public void setPaginas(List<Pagina> paginas) {
		this.paginas = paginas;
	}

	public String toString() {
		return this.nome;
	}
}