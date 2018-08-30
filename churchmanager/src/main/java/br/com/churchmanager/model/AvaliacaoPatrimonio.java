package br.com.churchmanager.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity(name = "avaliacao_patrimonio")
@Table(name = "avaliacao_patrimonio")
public class AvaliacaoPatrimonio extends EntidadeGenerica implements Serializable {

	private static final long serialVersionUID = 4287443468273L;

	@NotNull
	@ManyToOne(targetEntity = Patrimonio.class)
	@JoinColumn(name = "patrimonio_id", nullable = false)
	private Patrimonio patrimonio;

	@Size(max = 250)
	@Column(name = "observacao")
	private String observacao;

	@NotNull
	@Temporal(TemporalType.DATE)
	@Column(name = "data_avaliacao", nullable = false)
	private Date dataAvaliacao;

	@NotNull
	@DecimalMin("0.01")
	@Column(name = "valor_atual", nullable = false)
	private double valorAtual;

	public Patrimonio getPatrimonio() {
		return patrimonio;
	}

	public void setPatrimonio(Patrimonio patrimonio) {
		this.patrimonio = patrimonio;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public Date getDataAvaliacao() {
		return dataAvaliacao;
	}

	public void setDataAvaliacao(Date dataAvaliacao) {
		this.dataAvaliacao = dataAvaliacao;
	}

	public double getValorAtual() {
		return valorAtual;
	}

	public void setValorAtual(double valorAtual) {
		this.valorAtual = valorAtual;
	}

}