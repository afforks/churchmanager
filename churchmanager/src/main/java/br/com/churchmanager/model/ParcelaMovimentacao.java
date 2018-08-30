package br.com.churchmanager.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity(name = "parcela_movimentacao")
@Table(name = "parcela_movimentacao")
public class ParcelaMovimentacao extends EntidadeGenerica implements Serializable {

	private static final long serialVersionUID = 6311557355809561814L;

	@NotNull
	@ManyToOne(targetEntity = Movimentacao.class)
	@JoinColumn(name = "movimentacao_id", nullable = false)
	private Movimentacao movimentacao;

	@NotNull
	@Column(name = "valor_parcela", nullable = false)
	private double valorParcela;

	@NotNull
	@Column(name = "data_vencimento", nullable = false)
	@Temporal(TemporalType.DATE)
	private Date dataVencimento;

	@Column(name = "data_pagamento")
	@Temporal(TemporalType.DATE)
	private Date dataPagamento;

	@NotNull
	@Size(min = 3, max = 50)
	@Column(name = "nome")
	private String nome;

	@Size(max = 250)
	@Column(name = "descricao")
	private String descricao;

	@NotNull
	@Enumerated(EnumType.STRING)
	@Column(name = "status_movimentacao", nullable = false)
	private StatusMovimentacao statusMovimentacao;

	@NotNull
	@Column(name = "forma_movimentacao", nullable = false)
	@Enumerated(EnumType.STRING)
	private FormaMovimentacao formaMovimentacao;

	public Movimentacao getMovimentacao() {
		return movimentacao;
	}

	public void setMovimentacao(Movimentacao movimentacao) {
		this.movimentacao = movimentacao;
	}

	public double getValorParcela() {
		return valorParcela;
	}

	public void setValorParcela(double valorParcela) {
		this.valorParcela = valorParcela;
	}

	public Date getDataVencimento() {
		return dataVencimento;
	}

	public void setDataVencimento(Date dataVencimento) {
		this.dataVencimento = dataVencimento;
	}

	public Date getDataPagamento() {
		return dataPagamento;
	}

	public void setDataPagamento(Date dataPagamento) {
		this.dataPagamento = dataPagamento;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public StatusMovimentacao getStatusMovimentacao() {
		return statusMovimentacao;
	}

	public void setStatusMovimentacao(StatusMovimentacao statusMovimentacao) {
		this.statusMovimentacao = statusMovimentacao;
	}

	public FormaMovimentacao getFormaMovimentacao() {
		return formaMovimentacao;
	}

	public void setFormaMovimentacao(FormaMovimentacao formaMovimentacao) {
		this.formaMovimentacao = formaMovimentacao;
	}

	public boolean isStatusEmAbeto() {
		return getStatusMovimentacao().equals(StatusMovimentacao.EM_ABERTO);
	}

	public boolean isStatusPago() {
		return getStatusMovimentacao().equals(StatusMovimentacao.PAGO);
	}
	
	public boolean naoEstaEmAberto() {
		return !isStatusEmAbeto();
	}
}