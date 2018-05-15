package br.com.churchmanager.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.validator.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity(name = "evento")
@Table(name = "evento")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode(callSuper = true)
@Builder
public class Evento extends EntidadeGenerica {

	private static final long serialVersionUID = 8683670150324870932L;

	@Column(name = "nome", nullable = false, unique = true)
	private String nome;

	@NotBlank
	@Column(name = "dia", nullable = false)
	private String dia;

	@NotBlank
	@Column(name = "mes", nullable = false)
	private String mes;

	@Column(name = "descricao")
	private String descricao;

}