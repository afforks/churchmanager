package br.com.churchmanager.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import br.com.churchmanager.model.EntidadeGenerica;

@Entity(name = "atividade_eclesiastica")
@Table(name = "atividade_eclesiastica")
public class AtividadeEclesiastica extends EntidadeGenerica implements Serializable {
	private static final long serialVersionUID = 361521832597715321L;
	@Column(name = "nome", nullable = false, unique = true)
	private String nome;
	@Column(name = "descricao")
	private String descricao;

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

	public String toString() {
		return this.nome;
	}
}