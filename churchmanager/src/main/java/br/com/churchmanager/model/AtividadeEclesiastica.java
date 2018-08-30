package br.com.churchmanager.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

@Entity(name = "atividade_eclesiastica")
@Table(name = "atividade_eclesiastica")

public class AtividadeEclesiastica extends EntidadeGenerica implements Serializable {

	private static final long serialVersionUID = 361521832597715321L;

	@Size(min = 3, max = 50)
	@NotBlank
	@NotNull
	@Column(name = "nome", nullable = false, unique = true)
	private String nome;

	@Size(max = 250)
	@Column(name = "descricao")
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