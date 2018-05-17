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
//import javax.validation.constraints.NotNull;
//import javax.validation.constraints.Size;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

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
@ToString(of = { "movimentacao" })
@EqualsAndHashCode(of= {"id"}, callSuper=true)
@Builder
public class ParcelaMovimentacao extends EntidadeGenerica implements Serializable {

	private static final long serialVersionUID = 6311557355809561814L;

	@NotNull
	@ManyToOne(targetEntity = Movimentacao.class)
	@JoinColumn(name = "movimentacao_id", nullable = false)
	private Movimentacao movimentacao;

	@NotNull
	@Column(name = "valor_parcela", nullable = false)
	private float valorParcela;

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

}