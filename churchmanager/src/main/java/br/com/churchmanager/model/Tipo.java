package br.com.churchmanager.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import br.com.churchmanager.model.EntidadeGenerica;

@Entity(name = "tipo")
@Table(name = "tipo")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Tipo extends EntidadeGenerica implements Serializable {
	
	private static final long serialVersionUID = 2699793133797449401L;
	
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