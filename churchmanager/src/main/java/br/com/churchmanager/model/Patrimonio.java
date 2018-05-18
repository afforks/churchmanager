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
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
//import javax.validation.constraints.DecimalMin;
//import javax.validation.constraints.NotNull;
//import javax.validation.constraints.Size;
//
//import org.hibernate.annotations.Cache;
//import org.hibernate.annotations.CacheConcurrencyStrategy;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity(name = "patrimonio")
@Table(name = "patrimonio")
//@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(of = { "nome" })
@EqualsAndHashCode(of= {"id"}, callSuper=true)
@Builder
public class Patrimonio extends EntidadeGenerica implements Serializable {

	private static final long serialVersionUID = 12342873468273L;

	@NotNull
	@Size(min = 3, max = 50)
	@Column(name = "nome", nullable = false, unique = true)
	private String nome;

	@NotNull
	@OneToOne
	@JoinColumn(name = "tipo", nullable = false)
	private Tipo tipo;

	@NotNull
	@Enumerated(EnumType.STRING)
	@Column(name = "estado", nullable = false)
	
	private EstadoPatrimonio estado = EstadoPatrimonio.REGULAR;

	@NotNull
	@Enumerated(EnumType.STRING)
	@Column(name = "situacao", nullable = false)
	
	private Situacao situacao = Situacao.QUITADO;

	@NotNull
	@Temporal(TemporalType.DATE)
	@Column(name = "data_aquisicao", nullable = false)
	private Date dataAquisicao;

	@NotNull
	@DecimalMin("0.01")
	@Column(name = "valor_aquisicao", nullable = false)
	private double valorAquisicao;

	@NotNull
	@DecimalMin("0.01")
	@Column(name = "valor_atual", nullable = false)
	private double valorAtual;

	@Size(max = 250)
	@Column(name = "observacao")
	private String observacao;

	@OneToMany(mappedBy = "patrimonio", orphanRemoval = true, targetEntity = AvaliacaoPatrimonio.class, cascade = {
			CascadeType.ALL })
	
	private List<AvaliacaoPatrimonio> avaliacoes = new ArrayList();

	
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