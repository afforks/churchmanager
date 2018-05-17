package br.com.churchmanager.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity(name = "evento")
@Table(name = "evento")

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(of = { "nome" })
@EqualsAndHashCode(of = { "id" }, callSuper = true)
@Builder
public class Evento extends EntidadeGenerica {

	private static final long serialVersionUID = 8683670150324870932L;

	@NotNull
	@Size(min = 3, max = 60)
	@Column(name = "nome", nullable = false, unique = true)
	private String nome;

	@NotBlank
	@NotNull
	@Size(min = 1, max = 2)
	@Range(min = 1, max = 30)
	@Column(name = "dia", nullable = false)
	private String dia;

	@NotBlank
	@NotNull
	@Size(min = 1, max = 2)
	@Range(min = 1, max = 12)
	@Column(name = "mes", nullable = false)
	private String mes;

	@Size(max = 250)
	@Column(name = "descricao")
	private String descricao;

}