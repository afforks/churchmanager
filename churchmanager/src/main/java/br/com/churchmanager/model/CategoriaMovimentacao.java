package br.com.churchmanager.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity(name = "categoria_movimentacao")
@Table(name = "categoria_movimentacao")

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode(callSuper = true)
@Builder
public class CategoriaMovimentacao extends EntidadeGenerica implements Serializable {

	private static final long serialVersionUID = 244096197492206449L;

	@Column(name = "nome", nullable = false, unique = true)
	private String nome;

	@Column(name = "descricao")
	@Lob
	private String descricao;

}