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
//import javax.validation.constraints.NotNull;
//import javax.validation.constraints.Size;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

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
@ToString(of = { "nome" })
@EqualsAndHashCode(of= {"id"}, callSuper=true)
@Builder
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
	
}