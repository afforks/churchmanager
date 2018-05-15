package br.com.churchmanager.model;

import java.io.Serializable;
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

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity(name = "patrimonio")
@Table(name = "patrimonio")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode(callSuper = true)
@Builder
public class Patrimonio extends EntidadeGenerica implements Serializable {

	private static final long serialVersionUID = 12342873468273L;

	@Column(name = "nome", nullable = false, unique = true)
	private String nome;

	@OneToOne
	@JoinColumn(name = "tipo", nullable = false)
	private Tipo tipo;

	@Enumerated(EnumType.STRING)
	@Column(name = "estado", nullable = false)
	@Builder.Default
	private EstadoPatrimonio estado = EstadoPatrimonio.REGULAR;

	@Enumerated(EnumType.STRING)
	@Column(name = "situacao", nullable = false)
	@Builder.Default
	private Situacao situacao = Situacao.QUITADO;

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
			CascadeType.ALL })
	private List<AvaliacaoPatrimonio> avaliacoes;

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
}