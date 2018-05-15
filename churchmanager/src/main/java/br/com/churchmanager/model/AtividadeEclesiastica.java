package br.com.churchmanager.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity(name = "atividade_eclesiastica")
@Table(name = "atividade_eclesiastica")

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode(callSuper = true)
@Builder
public class AtividadeEclesiastica extends EntidadeGenerica implements Serializable {

	private static final long serialVersionUID = 361521832597715321L;

	@Column(name = "nome", nullable = false, unique = true)
	private String nome;

	@Column(name = "descricao")
	private String descricao;

}