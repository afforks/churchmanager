package br.com.churchmanager.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.validator.constraints.NotBlank;

import br.com.churchmanager.model.EntidadeGenerica;

@Entity(name = "evento")
@Table(name = "evento")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Evento extends EntidadeGenerica {
	private static final long serialVersionUID = 8683670150324870932L;
	@Column(name = "nome", nullable = false, unique = true)
	private String nome;
	@NotBlank
	@Column(name = "dia", nullable = false)
	private String dia;
	@NotBlank
	@Column(name = "mes", nullable = false)
	private String mes;
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

	public String getDia() {
		return this.dia;
	}

	public void setDia(String dia) {
		this.dia = dia;
	}

	public String getMes() {
		return this.mes;
	}

	public void setMes(String mes) {
		this.mes = mes;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String toString() {
		return this.nome;
	}
}