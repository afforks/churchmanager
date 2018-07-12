package br.com.churchmanager.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.Type;

import br.com.churchmanager.exception.NumeroParcelasInvalidoException;
import br.com.churchmanager.exception.ValorInvalidoException;
import br.com.churchmanager.util.DataUtil;
import br.com.churchmanager.util.MonetarioUtil;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "movimentacao")
@Table(name = "movimentacao")

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of= {"id"}, callSuper=true)
@Builder
public class Movimentacao extends EntidadeGenerica implements Serializable {

	private static final long serialVersionUID = 2815844645996443651L;

	@NotNull
	@Size(min = 3, max = 50)
	@Column(name = "nome", nullable = false)
	private String nome;

	@Size(max = 250)
	@Column(name = "descricao")
	private String descricao;

	@NotNull
	@DecimalMin("0.01")
	@Column(name = "valor", nullable = false)
	private BigDecimal valor;

	@Column(name = "numero_parcelas")
	@Min(1L)
	@Max(12L)
	@NotNull
	private int numeroParcelas = 1;

	@Column(name = "parcelado")
	@Type(type = "true_false")
	private boolean parcelado = false;

	@NotNull
	@Column(name = "tipo_movimentacao", nullable = false)
	@Enumerated(EnumType.STRING)
	private TipoMovimentacao tipoMovimentacao;

	@NotNull
	@Column(name = "forma_movimentacao")
	@Enumerated(EnumType.STRING)
	private FormaMovimentacao formaMovimentacao;

	@NotNull
	@Column(name = "data_base", nullable = false)
	@Temporal(TemporalType.DATE)
	private Date dataBase = new Date();

	@NotNull
	@Column(name = "data_vencimento", nullable = false)
	@Temporal(TemporalType.DATE)
	private Date dataVencimento;

	@NotNull
	@OneToOne(fetch = FetchType.EAGER, targetEntity = CategoriaMovimentacao.class)
	@JoinColumn(name = "categoria_id", nullable = false)
	private CategoriaMovimentacao categoriaMovimentacao;

	@NotNull
	@Enumerated(EnumType.STRING)
	@Column(name = "status_movimentacao", nullable = false)
	private StatusMovimentacao statusMovimentacao = StatusMovimentacao.EM_ABERTO;

	@OneToMany(mappedBy = "movimentacao", cascade = {
			CascadeType.ALL }, targetEntity = ParcelaMovimentacao.class, orphanRemoval = true)
	@Fetch(FetchMode.SUBSELECT)
	private List<ParcelaMovimentacao> parcelas = new ArrayList();

	public void gerarParcelas() {
		this.getParcelas().clear();

		for (int i = 0; i < this.getNumeroParcelas(); ++i) {
			int p = i + 1;
			ParcelaMovimentacao parcela = new ParcelaMovimentacao();
			parcela.setDataVencimento(DataUtil.gerarDataParaParcelamento(this.getDataVencimento(), i));
			parcela.setNome(this.nome);
			if (this.numeroParcelas == 1) {
				parcela.setDescricao("parcela única");
			} else {
				parcela.setDescricao(p + "ª parcela");
			}

			if (this.statusMovimentacao.equals(StatusMovimentacao.PAGO)) {
				parcela.setDataPagamento(this.getDataBase());
			}

			parcela.setMovimentacao(this);
			parcela.setFormaMovimentacao(this.getFormaMovimentacao());
			parcela.setStatusMovimentacao(this.getStatusMovimentacao());
			parcela.setValorParcela(this.getValor().doubleValue() / (double) this.getNumeroParcelas());
			this.getParcelas().add(parcela);
		}

	}

	public void setValor(BigDecimal valor) {
		if (valor == null || valor.doubleValue() <= 0) {
			throw new ValorInvalidoException("O valor deve ser maior que zero!");
		}
		this.valor = valor;
	}

	public void setNumeroParcelas(int numeroParcelas) {
		if (numeroParcelas <= 0) {
			throw new NumeroParcelasInvalidoException("O número de parcelas deve ser maior que zero!");
		}
		this.numeroParcelas = numeroParcelas;
	}

	public String toString() {
		return this.nome + ":  " + MonetarioUtil.formatarReal(this.valor.doubleValue()) + " em " + this.numeroParcelas
				+ " X " + this.parcelas;
	}

	public void atualizaParcela(ParcelaMovimentacao parcela) {
		for (int i = 0; i < this.getParcelas().size(); ++i) {
			if (this.getParcelas().get(i).equals(parcela)) {
				this.getParcelas().set(i, parcela);
				break;
			}
		}

	}
}