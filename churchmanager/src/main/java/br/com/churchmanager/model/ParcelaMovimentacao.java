package br.com.churchmanager.model;

import br.com.churchmanager.model.EntidadeGenerica;
import br.com.churchmanager.model.FormaMovimentacao;
import br.com.churchmanager.model.Movimentacao;
import br.com.churchmanager.model.StatusMovimentacao;
import br.com.churchmanager.util.MonetarioUtil;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity(name = "parcela_movimentacao")
@Table(name = "parcela_movimentacao")
public class ParcelaMovimentacao extends EntidadeGenerica implements Serializable {
	private static final long serialVersionUID = 6311557355809561814L;
	@ManyToOne(targetEntity = Movimentacao.class)
	@JoinColumn(name = "movimentacao_id", nullable = false)
	private Movimentacao movimentacao;
	@Column(name = "valor_parcela", nullable = false)
	private float valorParcela;
	@Column(name = "data_vencimento", nullable = false)
	@Temporal(TemporalType.DATE)
	private Date dataVencimento;
	@Column(name = "data_pagamento")
	@Temporal(TemporalType.DATE)
	private Date dataPagamento;
	@Column(name = "nome")
	private String nome;
	@Column(name = "descricao")
	@Lob
	private String descricao;
	@Enumerated(EnumType.STRING)
	@Column(name = "status_movimentacao", nullable = false)
	private StatusMovimentacao statusMovimentacao;
	@Column(name = "forma_movimentacao", nullable = false)
	@Enumerated(EnumType.STRING)
	private FormaMovimentacao formaMovimentacao;

	public ParcelaMovimentacao(String nome, Movimentacao movimentacao, float valorParcela, Date dataVencimento,
			String descricao, StatusMovimentacao statusMovimentacao) {
		this.statusMovimentacao = StatusMovimentacao.EM_ABERTO;
		this.formaMovimentacao = FormaMovimentacao.EM_ESPECIE;
		this.nome = nome;
		this.movimentacao = movimentacao;
		this.valorParcela = valorParcela;
		this.dataVencimento = dataVencimento;
		this.descricao = descricao;
		this.statusMovimentacao = statusMovimentacao;
	}

	public ParcelaMovimentacao() {
		this.statusMovimentacao = StatusMovimentacao.EM_ABERTO;
		this.formaMovimentacao = FormaMovimentacao.EM_ESPECIE;
	}

	public Movimentacao getMovimentacao() {
		return this.movimentacao;
	}

	public void setMovimentacao(Movimentacao movimentacao) {
		this.movimentacao = movimentacao;
	}

	public float getValorParcela() {
		return this.valorParcela;
	}

	public void setValorParcela(float valorParcela) {
		this.valorParcela = valorParcela;
	}

	public Date getDataVencimento() {
		return this.dataVencimento;
	}

	public void setDataVencimento(Date dataVencimento) {
		this.dataVencimento = dataVencimento;
	}

	public String getDescricao() {
		return this.descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public StatusMovimentacao getStatusMovimentacao() {
		return this.statusMovimentacao;
	}

	public void setStatusMovimentacao(StatusMovimentacao statusMovimentacao) {
		this.statusMovimentacao = statusMovimentacao;
	}

	public Date getDataPagamento() {
		return this.dataPagamento;
	}

	public void setDataPagamento(Date dataPagamento) {
		this.dataPagamento = dataPagamento;
	}

	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public FormaMovimentacao getFormaMovimentacao() {
		return this.formaMovimentacao;
	}

	public void setFormaMovimentacao(FormaMovimentacao formaMovimentacao) {
		this.formaMovimentacao = formaMovimentacao;
	}

	public String toString() {
		return this.descricao + ": " + MonetarioUtil.formatarReal((double) this.valorParcela);
	}
}