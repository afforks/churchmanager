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

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity(name = "perfil")
@Table(name = "perfil")

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode(callSuper = true)
@Builder
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Perfil extends EntidadeGenerica {

	private static final long serialVersionUID = 8683670150324870932L;

	public Perfil(String nome) {
		super();
		this.nome = nome;
	}

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

}