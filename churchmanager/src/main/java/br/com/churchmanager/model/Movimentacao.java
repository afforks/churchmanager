package br.com.churchmanager.model;

import br.com.churchmanager.model.CategoriaMovimentacao;
import br.com.churchmanager.model.EntidadeGenerica;
import br.com.churchmanager.model.FormaMovimentacao;
import br.com.churchmanager.model.ParcelaMovimentacao;
import br.com.churchmanager.model.StatusMovimentacao;
import br.com.churchmanager.model.TipoMovimentacao;
import br.com.churchmanager.util.DataUtil;
import br.com.churchmanager.util.MonetarioUtil;

import java.io.Serializable;
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
import javax.validation.constraints.Min;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.Type;

@Entity(name = "movimentacao")
@Table(name = "movimentacao")
public class Movimentacao extends EntidadeGenerica implements Serializable {
	private static final long serialVersionUID = 2815844645996443651L;
	@Column(name = "nome", nullable = false)
	private String nome;
	@Column(name = "descricao")
	private String descricao;
	@Column(name = "valor", nullable = false)
	private float valor;
	@Column(name = "numero_parcelas")
	@Min(1L)
	private int numeroParcelas = 1;
	@Column(name = "parcelado")
	@Type(type = "true_false")
	private boolean parcelado = false;
	@Column(name = "tipo_movimentacao", nullable = false)
	@Enumerated(EnumType.STRING)
	private TipoMovimentacao tipoMovimentacao;
	@Column(name = "forma_movimentacao")
	@Enumerated(EnumType.STRING)
	private FormaMovimentacao formaMovimentacao;
	@Column(name = "data_base", nullable = false)
	@Temporal(TemporalType.DATE)
	private Date dataBase = new Date();
	@Column(name = "data_vencimento", nullable = false)
	@Temporal(TemporalType.DATE)
	private Date dataVencimento;
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "categoria_id", nullable = false)
	private CategoriaMovimentacao categoriaMovimentacao;
	@Enumerated(EnumType.STRING)
	@Column(name = "status_movimentacao", nullable = false)
	private StatusMovimentacao statusMovimentacao;
	@OneToMany(mappedBy = "movimentacao", cascade = {
			CascadeType.ALL }, targetEntity = ParcelaMovimentacao.class, orphanRemoval = true)
	@Fetch(FetchMode.SUBSELECT)
	private List<ParcelaMovimentacao> parcelas;

	public Movimentacao() {
		this.statusMovimentacao = StatusMovimentacao.EM_ABERTO;
	}

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
			parcela.setValorParcela(this.getValor() / (float) this.getNumeroParcelas());
			this.getParcelas().add(parcela);
		}

	}

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

	public float getValor() {
		return this.valor;
	}

	public void setValor(float valor) {
		this.valor = valor;
	}

	public int getNumeroParcelas() {
		return this.numeroParcelas;
	}

	public void setNumeroParcelas(int numeroParcelas) {
		this.numeroParcelas = numeroParcelas;
	}

	public boolean isParcelado() {
		return this.parcelado;
	}

	public void setParcelado(boolean parcelado) {
		this.parcelado = parcelado;
	}

	public TipoMovimentacao getTipoMovimentacao() {
		return this.tipoMovimentacao;
	}

	public void setTipoMovimentacao(TipoMovimentacao tipoMovimentacao) {
		this.tipoMovimentacao = tipoMovimentacao;
	}

	public Date getDataBase() {
		return this.dataBase;
	}

	public void setDataBase(Date dataBase) {
		this.dataBase = dataBase;
	}

	public CategoriaMovimentacao getCategoriaMovimentacao() {
		return this.categoriaMovimentacao;
	}

	public void setCategoriaMovimentacao(CategoriaMovimentacao categoriaMovimentacao) {
		this.categoriaMovimentacao = categoriaMovimentacao;
	}

	public StatusMovimentacao getStatusMovimentacao() {
		return this.statusMovimentacao;
	}

	public void setStatusMovimentacao(StatusMovimentacao statusMovimentacao) {
		this.statusMovimentacao = statusMovimentacao;
	}

	public FormaMovimentacao getFormaMovimentacao() {
		return this.formaMovimentacao;
	}

	public void setFormaMovimentacao(FormaMovimentacao formaMovimentacao) {
		this.formaMovimentacao = formaMovimentacao;
	}

	public List<ParcelaMovimentacao> getParcelas() {
		if (this.parcelas == null) {
			this.parcelas = new ArrayList<>();
		}

		return this.parcelas;
	}

	public void setParcelas(List<ParcelaMovimentacao> parcelas) {
		this.parcelas = parcelas;
	}

	public Date getDataVencimento() {
		if (this.dataVencimento == null) {
			this.dataVencimento = new Date();
		}

		return this.dataVencimento;
	}

	public void setDataVencimento(Date dataVencimento) {
		this.dataVencimento = dataVencimento;
	}

	public String toString() {
		return this.nome + ":  " + MonetarioUtil.formatarReal((double) this.valor) + " em " + this.numeroParcelas
				+ " X " + this.parcelas;
	}

	public void atualizaParcela(ParcelaMovimentacao parcela) {
		for (int i = 0; i < this.getParcelas().size(); ++i) {
			if (((ParcelaMovimentacao) this.getParcelas().get(i)).equals(parcela)) {
				this.getParcelas().set(i, parcela);
				break;
			}
		}

	}
}