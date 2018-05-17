package br.com.churchmanager.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

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
@ToString(of = { "nome" })
@EqualsAndHashCode(of= {"id"}, callSuper=true)
@Builder
public class CategoriaMovimentacao extends EntidadeGenerica implements Serializable {

	private static final long serialVersionUID = 244096197492206449L;

	@NotNull
	@Size(min = 3, max = 60)
	@Column(name = "nome", nullable = false, unique = true)
	private String nome;

	@Size(max = 250)
	@Column(name = "descricao")
	private String descricao;

}