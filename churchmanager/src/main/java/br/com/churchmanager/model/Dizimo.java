package br.com.churchmanager.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Type;

import br.com.churchmanager.util.DataUtil;
import br.com.churchmanager.util.MonetarioUtil;

@Entity(name = "dizimo")
@Table(name = "dizimo")
public class Dizimo extends EntidadeGenerica implements Serializable {

	private static final long serialVersionUID = 4517030116936446515L;

	@NotNull
	@ManyToOne(targetEntity = Pessoa.class)
	@JoinColumn(name = "pessoa_id", nullable = false)
	private Pessoa pessoa;

	@NotNull
	@DecimalMin("0.01")
	@Column(name = "valor_dizimo", nullable = false)
	private BigDecimal valorDizimo = new BigDecimal(0);

	@NotNull
	@Column(name = "valor_oferta", nullable = false)
	private BigDecimal valorOferta = new BigDecimal(0);

	@Transient
	private BigDecimal valorTotal = new BigDecimal(0);

	@NotNull
	@Temporal(TemporalType.DATE)
	@Past
	@Column(name = "data_referencia", nullable = false)
	private Date dataReferencia = DataUtil.mesAnterior(new Date());

	@NotNull
	@Temporal(TemporalType.DATE)
	@Column(name = "data_recebimento", nullable = false)
	@Past
	private Date dataRecebimento = new Date();

	@Size(max = 250)
	@Column(name = "observacao")
	private String observcao;

	@Type(type = "true_false")
	@Column(name = "is_13")
	private boolean is13 = false;

	public BigDecimal getValorTotal() {
		this.valorTotal = new BigDecimal(this.valorDizimo.doubleValue() + this.valorOferta.doubleValue());
		return this.valorTotal;
	}

	public String toString() {
		return DataUtil.dateParaString(this.dataRecebimento) + ": "
				+ MonetarioUtil.formatarReal(this.valorDizimo.doubleValue() + this.valorOferta.doubleValue());
	}

	// ************************************************************************S

	public boolean isIs13() {
		return is13;
	}

	public void setIs13(boolean is13) {
		this.is13 = is13;
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	public BigDecimal getValorDizimo() {
		return valorDizimo;
	}

	public void setValorDizimo(BigDecimal valorDizimo) {
		this.valorDizimo = valorDizimo;
	}

	public BigDecimal getValorOferta() {
		return valorOferta;
	}

	public void setValorOferta(BigDecimal valorOferta) {
		this.valorOferta = valorOferta;
	}

	public Date getDataReferencia() {
		return dataReferencia;
	}

	public void setDataReferencia(Date dataReferencia) {
		this.dataReferencia = dataReferencia;
	}

	public Date getDataRecebimento() {
		return dataRecebimento;
	}

	public void setDataRecebimento(Date dataRecebimento) {
		this.dataRecebimento = dataRecebimento;
	}

	public String getObservcao() {
		return observcao;
	}

	public void setObservcao(String observcao) {
		this.observcao = observcao;
	}

	public void setValorTotal(BigDecimal valorTotal) {
		this.valorTotal = valorTotal;
	}

}