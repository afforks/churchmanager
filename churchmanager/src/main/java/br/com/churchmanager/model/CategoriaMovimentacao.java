package br.com.churchmanager.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.Table;

import br.com.churchmanager.model.EntidadeGenerica;

@Entity(name = "categoria_movimentacao")
@Table(name = "categoria_movimentacao")
public class CategoriaMovimentacao extends EntidadeGenerica implements Serializable {
	private static final long serialVersionUID = 244096197492206449L;
	@Column(name = "nome", nullable = false, unique = true)
	private String nome;
	@Column(name = "descricao")
	@Lob
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