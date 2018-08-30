package br.com.churchmanager.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import br.com.churchmanager.util.custom.Cep;

@Embeddable
public class Endereco implements Serializable {
	private static final long serialVersionUID = 7606653748140748746L;

	@Column(name = "logradouro")
	private String logradouro;

	@Column(name = "referencia")
	private String referencia;

	@Column(name = "bairro")
	private String bairro;

	@Column(name = "cep")
	@Cep
	private String cep = "62.940-000";

	@Column(name = "cidade")
	private String cidade = "Morada Nova";

	@Column(name = "estado")
	@Enumerated(EnumType.STRING)
	private Estado estado = Estado.CE;

	@Column(name = "pais")
	private String pais = "Brasil";

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public String getReferencia() {
		return referencia;
	}

	public void setReferencia(String referencia) {
		this.referencia = referencia;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	public String getPais() {
		return pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}

}