package br.com.churchmanager.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity(name = "cargo")
@Table(name = "cargo")

public class Cargo extends EntidadeGenerica implements Serializable {

	private static final long serialVersionUID = 6885908374225768867L;

	@NotNull
	@Size(min = 3, max = 60)
	@Column(name = "nome", nullable = false, unique = true)
	private String nome;

	@Column(name = "descricao")
	@Size(max = 250)
	private String descricao;

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

}