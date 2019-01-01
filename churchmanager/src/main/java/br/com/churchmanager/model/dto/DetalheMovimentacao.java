package br.com.churchmanager.model.dto;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;

import br.com.churchmanager.model.StatusMovimentacao;
import br.com.churchmanager.model.TipoMovimentacao;

public class DetalheMovimentacao implements Serializable {
	private static final long serialVersionUID = -1331405134950674092L;
	private BigInteger mes;
	private BigInteger ano;
	private String nome;
	private String descricao;
	private Date dataVencimento;
	private Date dataPagamento;
	private double valor;
	private String tipo;
	private String forma;
	private String status;

	public BigInteger getMes() {
		return this.mes;
	}

	public void setMes(BigInteger mes) {
		this.mes = mes;
	}

	public BigInteger getAno() {
		return this.ano;
	}

	public void setAno(BigInteger ano) {
		this.ano = ano;
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

	public Date getDataVencimento() {
		return this.dataVencimento;
	}

	public void setDataVencimento(Date dataVencimento) {
		this.dataVencimento = dataVencimento;
	}

	public Date getDataPagamento() {
		return this.dataPagamento;
	}

	public void setDataPagamento(Date dataPagamento) {
		this.dataPagamento = dataPagamento;
	}

	public double getValor() {
		return this.valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	public String getTipoDescricao() {
		return TipoMovimentacao.tipoPorDescricao(this.tipo);
	}

	public String getCorTipo() {
		return TipoMovimentacao.backgroundPorDescricao(this.tipo);
	}

	public String getTipo() {
		return this.tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getStatusDescricao() {
		return StatusMovimentacao.statusPorDescricao(this.status);
	}

	public String getCorStatus() {
		return StatusMovimentacao.corPorDescricao(this.status);
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getForma() {
		return this.forma;
	}

	public void setForma(String forma) {
		this.forma = forma;
	}
}