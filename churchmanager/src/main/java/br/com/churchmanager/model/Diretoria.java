package br.com.churchmanager.model;

import br.com.churchmanager.model.EntidadeGenerica;
import br.com.churchmanager.model.PessoaCargo;
import br.com.churchmanager.util.DataUtil;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity(name = "diretoria")
@Table(name = "diretoria")
public class Diretoria extends EntidadeGenerica implements Serializable {
	private static final long serialVersionUID = 2345438374225768867L;
	@Column(name = "nome", nullable = false, unique = true)
	private String nome;
	@Lob
	@Column(name = "descricao")
	private String descricao;
	@ManyToMany(fetch = FetchType.EAGER)
	@Fetch(FetchMode.SUBSELECT)
	@JoinTable(name = "diretoria_pessoa_cargo", joinColumns = {
			@JoinColumn(name = "diretoria_id")}, inverseJoinColumns = {@JoinColumn(name = "pessoa_cargo_id")})
	private List<PessoaCargo> pessoaCargos;
	@Temporal(TemporalType.DATE)
	@Column(name = "data_inicio", nullable = false)
	private Date inicio = DataUtil.stringParaDate("01/01/" + DataUtil.ano());
	@Temporal(TemporalType.DATE)
	@Column(name = "data_termino", nullable = false)
	private Date termino = DataUtil.stringParaDate("31/12/" + DataUtil.ano());

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

	public List<PessoaCargo> getPessoaCargos() {
		if (this.pessoaCargos == null) {
			this.pessoaCargos = new ArrayList<>();
		}

		return this.pessoaCargos;
	}

	public void setPessoaCargos(List<PessoaCargo> pessoaCargos) {
		this.pessoaCargos = pessoaCargos;
	}

	public Date getInicio() {
		return this.inicio;
	}

	public void setInicio(Date inicio) {
		this.inicio = inicio;
	}

	public Date getTermino() {
		return this.termino;
	}

	public void setTermino(Date termino) {
		this.termino = termino;
	}
}