package br.com.churchmanager.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity(name = "perfil")
@Table(name = "perfil")
public class Perfil extends EntidadeGenerica implements Serializable {

	private static final long serialVersionUID = 8683670150324870932L;

	@NotNull
	@Size(min = 3, max = 50)
	@Column(name = "nome", nullable = false, unique = true)
	private String nome;

	@Size(max = 250)
	@Column(name = "descricao")
	private String descricao;

	@NotNull
	@ManyToMany(fetch = FetchType.EAGER)
	@Fetch(FetchMode.SUBSELECT)
	@JoinTable(name = "perfil_pagina", joinColumns = { @JoinColumn(name = "perfil_id") }, inverseJoinColumns = {
			@JoinColumn(name = "pagina_id") })

	private List<Pagina> paginas = new ArrayList<>();

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public List<Pagina> getPaginas() {
		return paginas;
	}

	public void setPaginas(List<Pagina> paginas) {
		this.paginas = paginas;
	}

}