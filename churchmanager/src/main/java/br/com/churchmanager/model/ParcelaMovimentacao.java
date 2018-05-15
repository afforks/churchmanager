package br.com.churchmanager.model;

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

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity(name = "parcela_movimentacao")
@Table(name = "parcela_movimentacao")

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode(callSuper = true)
@Builder
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

}