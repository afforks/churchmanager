package br.com.churchmanager.model.filter;

import java.io.Serializable;
import java.util.Date;

public class DizimoFilter implements Filter, Serializable {

	private static final long serialVersionUID = -5696573251550574369L;

	private String nomePessoa;
	private double valorDizimo;
	private double valorOferta;
	private Date dataReferencia;
	private Date dataRecebimento;
	private String mes;
	private String ano;

	public String getNomePessoa() {
		return nomePessoa;
	}

	public void setNomePessoa(String nomePessoa) {
		this.nomePessoa = nomePessoa;
	}

	public double getValorDizimo() {
		return valorDizimo;
	}

	public void setValorDizimo(double valorDizimo) {
		this.valorDizimo = valorDizimo;
	}

	public double getValorOferta() {
		return valorOferta;
	}

	public void setValorOferta(double valorOferta) {
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

	public String getMes() {
		return mes;
	}

	public void setMes(String mes) {
		this.mes = mes;
	}

	public String getAno() {
		return ano;
	}

	public void setAno(String ano) {
		this.ano = ano;
	}

}