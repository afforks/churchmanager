package br.com.churchmanager.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity(name = "tipo")
@Table(name = "tipo")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode(callSuper = true)
@Builder
public class Tipo extends EntidadeGenerica implements Serializable {

	private static final long serialVersionUID = 2699793133797449401L;

	@Column(name = "nome", nullable = false, unique = true)
	private String nome;

	@Column(name = "descricao")
	private String descricao;
}