package br.com.churchmanager.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.Table;

import br.com.churchmanager.model.EntidadeGenerica;

@Entity(name = "cargo")
@Table(name = "cargo")
public class Cargo extends EntidadeGenerica implements Serializable {
	
	private static final long serialVersionUID = 6885908374225768867L;
	
	@Column(name = "nome", nullable = false, unique = true)
	private String nome;
	
	@Lob
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

	@Override
	public String toString() {
		return "Cargo [nome=" + nome + "]";
	}
}