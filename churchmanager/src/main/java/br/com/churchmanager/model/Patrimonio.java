package br.com.churchmanager.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.DecimalMin;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import br.com.churchmanager.model.AvaliacaoPatrimonio;
import br.com.churchmanager.model.EntidadeGenerica;
import br.com.churchmanager.model.EstadoPatrimonio;
import br.com.churchmanager.model.Situacao;
import br.com.churchmanager.model.Tipo;

@Entity(name = "patrimonio")
@Table(name = "patrimonio")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Patrimonio extends EntidadeGenerica implements Serializable {
	private static final long serialVersionUID = 12342873468273L;
	@Column(name = "nome", nullable = false, unique = true)
	private String nome;
	@OneToOne
	@JoinColumn(name = "tipo", nullable = false)
	private Tipo tipo;
	@Enumerated(EnumType.STRING)
	@Column(name = "estado", nullable = false)
	private EstadoPatrimonio estado;
	@Enumerated(EnumType.STRING)
	@Column(name = "situacao", nullable = false)
	private Situacao situacao;
	@Temporal(TemporalType.DATE)
	@Column(name = "data_aquisicao", nullable = false)
	private Date dataAquisicao;
	@DecimalMin("0.01")
	@Column(name = "valor_aquisicao", nullable = false)
	private double valorAquisicao;
	@DecimalMin("0.01")
	@Column(name = "valor_atual", nullable = false)
	private double valorAtual;
	@Lob
	@Column(name = "observacao")
	private String observacao;
	@OneToMany(mappedBy = "patrimonio", orphanRemoval = true, targetEntity = AvaliacaoPatrimonio.class, cascade = {
			CascadeType.ALL})
	private List<AvaliacaoPatrimonio> avaliacoes;

	public Patrimonio() {
		this.estado = EstadoPatrimonio.REGULAR;
		this.situacao = Situacao.QUITADO;
	}

	public void adicionar(AvaliacaoPatrimonio avaliacao) {
		avaliacao.setPatrimonio(this);
		this.getAvaliacoes().add(avaliacao);
	}

	public void atualizar(AvaliacaoPatrimonio avaliacao) {
		int id = this.indiceDe(avaliacao);
		this.getAvaliacoes().set(id, avaliacao);
	}

	public void remover(AvaliacaoPatrimonio avaliacao) {
		int id = this.indiceDe(avaliacao);
		this.getAvaliacoes().remove(id);
	}

	public int indiceDe(AvaliacaoPatrimonio avaliacao) {
		int indice = -1;

		for (int i = 0; i < this.getAvaliacoes().size(); ++i) {
			if (this.getAvaliacoes().get(i) == avaliacao) {
				indice = i;
				break;
			}
		}

		return indice;
	}

	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Tipo getTipo() {
		return this.tipo;
	}

	public void setTipo(Tipo tipo) {
		this.tipo = tipo;
	}

	public EstadoPatrimonio getEstado() {
		return this.estado;
	}

	public void setEstado(EstadoPatrimonio estado) {
		this.estado = estado;
	}

	public Situacao getSituacao() {
		return this.situacao;
	}

	public void setSituacao(Situacao situacao) {
		this.situacao = situacao;
	}

	public Date getDataAquisicao() {
		return this.dataAquisicao;
	}

	public void setDataAquisicao(Date dataAquisicao) {
		this.dataAquisicao = dataAquisicao;
	}

	public double getValorAquisicao() {
		return this.valorAquisicao;
	}

	public void setValorAquisicao(double valorAquisicao) {
		this.valorAquisicao = valorAquisicao;
	}

	public double getValorAtual() {
		return this.valorAtual;
	}

	public void setValorAtual(double valorAtual) {
		this.valorAtual = valorAtual;
	}

	public String getObservacao() {
		return this.observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public List<AvaliacaoPatrimonio> getAvaliacoes() {
		if (this.avaliacoes == null) {
			this.avaliacoes = new ArrayList<>();
		}

		return this.avaliacoes;
	}

	public void setAvaliacoes(List<AvaliacaoPatrimonio> avaliacoes) {
		this.avaliacoes = avaliacoes;
	}

	public String toString() {
		return this.nome;
	}
}