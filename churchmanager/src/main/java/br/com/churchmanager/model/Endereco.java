package br.com.churchmanager.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.Pattern;

import br.com.churchmanager.model.Estado;

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
	@Pattern(regexp = "\\d{5}-\\d{3}", message = "Cep com formato inválido")
	private String cep = "62940-000";

	@Column(name = "cidade")
	private String cidade = "Morada Nova";

	@Column(name = "estado")
	@Enumerated(EnumType.STRING)
	private Estado estado;

	@Column(name = "pais")
	private String pais;

	public Endereco() {
		this.estado = Estado.CE;
		this.pais = "Brasil";
	}

	public String getLogradouro() {
		return this.logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public String getReferencia() {
		return this.referencia;
	}

	public void setReferencia(String referencia) {
		this.referencia = referencia;
	}

	public String getBairro() {
		return this.bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCep() {
		return this.cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getCidade() {
		return this.cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public Estado getEstado() {
		return this.estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	public String getPais() {
		return this.pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}
}