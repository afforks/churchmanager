package br.com.churchmanager.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

@Entity(name = "evento")
@Table(name = "evento")
public class Evento extends EntidadeGenerica {

	private static final long serialVersionUID = 8683670150324870932L;

	@NotNull
	@Size(min = 3, max = 60)
	@Column(name = "nome", nullable = false, unique = true)
	private String nome;

	@NotBlank
	@NotNull
	@Size(min = 1, max = 2)
	@Min(1L)
	@Max(31L)
	@Column(name = "dia", nullable = false)
	private String dia;

	@NotBlank
	@NotNull
	@Size(min = 1, max = 2)
	@Min(1L)
	@Max(12L)
	@Column(name = "mes", nullable = false)
	private String mes;

	@Size(max = 250)
	@Column(name = "descricao")
	private String descricao;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDia() {
		return dia;
	}

	public void setDia(String dia) {
		this.dia = dia;
	}

	public String getMes() {
		return mes;
	}

	public void setMes(String mes) {
		this.mes = mes;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

}