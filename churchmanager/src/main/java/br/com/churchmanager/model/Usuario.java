package br.com.churchmanager.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.validator.constraints.Email;

@Entity(name = "usuario")
@Table(name = "usuario")
public class Usuario extends EntidadeGenerica implements Serializable {

	private static final long serialVersionUID = 1L;

	@NotNull
	@Size(min = 6, max = 50)
	@Column(name = "nome_completo", nullable = false)
	private String nomeCompleto;

	@Size(min = 6, max = 50)
	@Email
	@NotNull
	@Column(name = "email", nullable = false, unique = true)
	private String email;

	@Size(min = 8, max = 50)
	@NotNull
	@Column(name = "senha", nullable = false)
	private String senha;

	@OneToOne(targetEntity = Perfil.class)
	@JoinColumn(name = "perfil_id", nullable = false)
	@NotNull
	private Perfil perfil = new Perfil();

	@ManyToMany(fetch = FetchType.EAGER, cascade = { CascadeType.ALL })
	@Fetch(FetchMode.SUBSELECT)
	@JoinTable(name = "usuario_pagina", joinColumns = { @JoinColumn(name = "usuario_id") }, inverseJoinColumns = {
			@JoinColumn(name = "pagina_id") })
	@NotNull

	private List<Pagina> paginas = new ArrayList<>();

	public String getNomeCompleto() {
		return nomeCompleto;
	}

	public void setNomeCompleto(String nomeCompleto) {
		this.nomeCompleto = nomeCompleto;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Perfil getPerfil() {
		return perfil;
	}

	public void setPerfil(Perfil perfil) {
		this.perfil = perfil;
	}

	public List<Pagina> getPaginas() {
		return paginas;
	}

	public void setPaginas(List<Pagina> paginas) {
		this.paginas = paginas;
	}

}