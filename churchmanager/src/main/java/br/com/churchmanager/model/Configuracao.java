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

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "configuracao")
@Table(name = "configuracao")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = { "id" }, callSuper = true)
@Builder
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

}
