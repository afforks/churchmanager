package br.com.churchmanager.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity(name = "pessoa_cargo")
@Table(name = "pessoa_cargo")

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(of = { "pessoa", "cargo" })
@EqualsAndHashCode(of= {"id"}, callSuper=true)
@Builder
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

}