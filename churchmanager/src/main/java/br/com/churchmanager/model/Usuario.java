package br.com.churchmanager.model;

import java.io.Serializable;
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

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity(name = "usuario")
@Table(name = "usuario")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode(callSuper=true)
@Builder
public class Usuario extends EntidadeGenerica implements Serializable {

	private static final long serialVersionUID = 1L;

	@NotEmpty
	@NotBlank
	@Column(name = "nome_completo", nullable = false)
	private String nomeCompleto;

	@Email
	@Column(name = "email", nullable = false, unique = true)
	private String email;

	@Column(name = "senha", nullable = false)
	private String senha;

	@OneToOne
	@JoinColumn(name = "perfil_id", nullable = false)
	private Perfil perfil;

	@ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.ALL})
	@Fetch(FetchMode.SUBSELECT)
	@JoinTable(name = "usuario_pagina", joinColumns = { @JoinColumn(name = "usuario_id") }, inverseJoinColumns = {
			@JoinColumn(name = "pagina_id")})
	@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
	private List<Pagina> paginas;

	
	public Usuario(String nomeCompleto, String email) {
		this.nomeCompleto = nomeCompleto;
		this.email = email;
	}
}