package br.com.churchmanager.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity(name = "pessoa_cargo")
@Table(name = "pessoa_cargo")
public class PessoaCargo extends EntidadeGenerica implements Serializable {

	private static final long serialVersionUID = 985908374225768867L;

	@OneToOne
	@JoinColumn(name = "pessoa_id", nullable = false)
	@NotNull
	private Pessoa pessoa;

	@OneToOne
	@JoinColumn(name = "cargo_id", nullable = false)
	@NotNull
	private Cargo cargo;

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	public Cargo getCargo() {
		return cargo;
	}

	public void setCargo(Cargo cargo) {
		this.cargo = cargo;
	}

}