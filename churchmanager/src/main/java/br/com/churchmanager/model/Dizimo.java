package br.com.churchmanager.model;

import br.com.churchmanager.model.EntidadeGenerica;
import br.com.churchmanager.model.Pessoa;
import br.com.churchmanager.util.DataUtil;
import br.com.churchmanager.util.MonetarioUtil;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.DecimalMin;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Type;

@Entity(name = "dizimo")
@Table(name = "dizimo")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Dizimo extends EntidadeGenerica implements Serializable {
	private static final long serialVersionUID = 4517030116936446515L;
	@ManyToOne(targetEntity = Pessoa.class)
	@JoinColumn(name = "pessoa_id", nullable = false)
	private Pessoa pessoa;
	@DecimalMin("0.01")
	@Column(name = "valor_dizimo", nullable = false)
	private double valorDizimo;
	@Column(name = "valor_oferta", nullable = false)
	private double valorOferta;
	@Transient
	private double valorTotal;
	@Temporal(TemporalType.DATE)
	@Column(name = "data_referencia", nullable = false)
	private Date dataReferencia = DataUtil.mesAnterior(new Date());
	@Temporal(TemporalType.DATE)
	@Column(name = "data_recebimento", nullable = false)
	private Date dataRecebimento = new Date();
	@Lob
	@Column(name = "observacao")
	private String observcao;
	@Type(type = "true_false")
	@Column(name = "is_13")
	private boolean is13 = false;

	public Pessoa getPessoa() {
		return this.pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	public double getValorDizimo() {
		return this.valorDizimo;
	}

	public void setValorDizimo(double valorDizimo) {
		this.valorDizimo = valorDizimo;
	}

	public double getValorOferta() {
		return this.valorOferta;
	}

	public void setValorOferta(double valorOferta) {
		this.valorOferta = valorOferta;
	}

	public Date getDataReferencia() {
		return this.dataReferencia;
	}

	public void setDataReferencia(Date dataReferencia) {
		this.dataReferencia = dataReferencia;
	}

	public Date getDataRecebimento() {
		return this.dataRecebimento;
	}

	public void setDataRecebimento(Date dataRecebimento) {
		this.dataRecebimento = dataRecebimento;
	}

	public String getObservcao() {
		return this.observcao;
	}

	public void setObservcao(String observcao) {
		this.observcao = observcao;
	}

	public double getValorTotal() {
		this.valorTotal = this.valorDizimo + this.valorOferta;
		return this.valorTotal;
	}

	public void setValorTotal(double valorTotal) {
		this.valorTotal = valorTotal;
	}

	public boolean isIs13() {
		return this.is13;
	}

	public void setIs13(boolean is13) {
		this.is13 = is13;
	}

	public String toString() {
		return DataUtil.dateParaString(this.dataRecebimento) + ": "
				+ MonetarioUtil.formatarReal(this.valorDizimo + this.valorOferta);
	}
}