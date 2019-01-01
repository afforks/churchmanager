package br.com.churchmanager.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.validator.constraints.NotEmpty;

import br.com.churchmanager.util.DataUtil;

@Entity(name = "diretoria")
@Table(name = "diretoria")

public class Diretoria extends EntidadeGenerica implements Serializable {

	private static final long serialVersionUID = 2345438374225768867L;

	@NotNull
	@Size(min = 3, max = 60)
	@Column(name = "nome", nullable = false, unique = true)
	private String nome;

	@Size(max = 250)
	@Column(name = "descricao")
	private String descricao;

	@NotNull
	@NotEmpty

	@Fetch(FetchMode.SUBSELECT)
	@JoinTable(name = "diretoria_pessoa_cargo", joinColumns = {
			@JoinColumn(name = "diretoria_id") }, inverseJoinColumns = { @JoinColumn(name = "pessoa_cargo_id") })
	@ManyToMany(fetch = FetchType.EAGER, targetEntity = PessoaCargo.class)
	private List<PessoaCargo> pessoaCargos = new ArrayList<>();

	@NotNull

	@Temporal(TemporalType.DATE)
	@Column(name = "data_inicio", nullable = false)
	private Date inicio = DataUtil.stringParaDate("01/01/" + DataUtil.ano());

	@NotNull

	@Temporal(TemporalType.DATE)
	@Column(name = "data_termino", nullable = false)
	private Date termino = DataUtil.stringParaDate("31/12/" + DataUtil.ano());

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

	public List<PessoaCargo> getPessoaCargos() {
		return pessoaCargos;
	}

	public void setPessoaCargos(List<PessoaCargo> pessoaCargos) {
		this.pessoaCargos = pessoaCargos;
	}

	public Date getInicio() {
		return inicio;
	}

	public void setInicio(Date inicio) {
		this.inicio = inicio;
	}

	public Date getTermino() {
		return termino;
	}

	public void setTermino(Date termino) {
		this.termino = termino;
	}

}