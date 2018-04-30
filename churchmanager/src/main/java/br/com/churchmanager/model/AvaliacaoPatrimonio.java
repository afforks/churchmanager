package br.com.churchmanager.model;

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
import javax.validation.constraints.DecimalMin;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import br.com.churchmanager.model.EntidadeGenerica;
import br.com.churchmanager.model.Patrimonio;

@Entity(name = "avaliacao_patrimonio")
@Table(name = "avaliacao_patrimonio")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class AvaliacaoPatrimonio extends EntidadeGenerica implements Serializable {

	private static final long serialVersionUID = 4287443468273L;

	@ManyToOne(targetEntity = Patrimonio.class)
	@JoinColumn(name = "patrimonio_id", nullable = false)
	private Patrimonio patrimonio;

	@Lob
	@Column(name = "observacao")
	private String observacao;

	@Temporal(TemporalType.DATE)
	@Column(name = "data_avaliacao", nullable = false)
	private Date dataAvaliacao;

	@DecimalMin("0.01")
	@Column(name = "valor_atual", nullable = false)
	private double valorAtual;

	public String getObservacao() {
		return this.observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public Date getDataAvaliacao() {
		return this.dataAvaliacao;
	}

	public void setDataAvaliacao(Date dataAvaliacao) {
		this.dataAvaliacao = dataAvaliacao;
	}

	public double getValorAtual() {
		return this.valorAtual;
	}

	public void setValorAtual(double valorAtual) {
		this.valorAtual = valorAtual;
	}

	public Patrimonio getPatrimonio() {
		return this.patrimonio;
	}

	public void setPatrimonio(Patrimonio patrimonio) {
		this.patrimonio = patrimonio;
	}
}