package br.com.churchmanager.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.br.CNPJ;

@Entity(name = "configuracao")
@Table(name = "configuracao")
public class Configuracao extends EntidadeGenerica {

	private static final long serialVersionUID = -2791052442461724363L;

	@NotBlank
	@Column(name = "nome_igreja", nullable = false)
	private String nomeDaIgreja;

	@NotBlank
	@CNPJ
	@Column(name = "cnpj", nullable = false)
	private String cnpj;

	@NotBlank
	@Column(name = "nome_banco", nullable = false)
	private String nomeDoBanco;

	@NotBlank
	@Column(name = "conta_banco", nullable = false)
	private String conta;

	@NotBlank
	@Column(name = "ag_banco", nullable = false)
	private String agencia;

	@NotBlank
	@Column(name = "nome_dirigente", nullable = false)
	private String nomeDoDirigente;

	@ElementCollection
	@CollectionTable(name = "config_telefones", joinColumns = { @JoinColumn(name = "configuracao_id") })
	@Column(name = "telefone")
	private List<String> telefones = new ArrayList<>();

	@ElementCollection
	@CollectionTable(name = "config_emails", joinColumns = { @JoinColumn(name = "configuracao_id") })
	@Column(name = "email")
	private List<String> emails = new ArrayList<>();

	@Embedded
	private Endereco endereco = new Endereco();

	public String getNomeDaIgreja() {
		return nomeDaIgreja;
	}

	public void setNomeDaIgreja(String nomeDaIgreja) {
		this.nomeDaIgreja = nomeDaIgreja;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public String getNomeDoBanco() {
		return nomeDoBanco;
	}

	public void setNomeDoBanco(String nomeDoBanco) {
		this.nomeDoBanco = nomeDoBanco;
	}

	public String getConta() {
		return conta;
	}

	public void setConta(String conta) {
		this.conta = conta;
	}

	public String getAgencia() {
		return agencia;
	}

	public void setAgencia(String agencia) {
		this.agencia = agencia;
	}

	public String getNomeDoDirigente() {
		return nomeDoDirigente;
	}

	public void setNomeDoDirigente(String nomeDoDirigente) {
		this.nomeDoDirigente = nomeDoDirigente;
	}

	public List<String> getTelefones() {
		return telefones;
	}

	public void setTelefones(List<String> telefones) {
		this.telefones = telefones;
	}

	public List<String> getEmails() {
		return emails;
	}

	public void setEmails(List<String> emails) {
		this.emails = emails;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

}
