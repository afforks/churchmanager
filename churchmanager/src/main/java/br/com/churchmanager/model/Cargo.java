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

@Entity(name = "cargo")
@Table(name = "cargo")

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode(callSuper = true)
@Builder
public class Cargo extends EntidadeGenerica implements Serializable {

	private static final long serialVersionUID = 6885908374225768867L;

	@Column(name = "nome", nullable = false, unique = true)
	private String nome;

	@Lob
	@Column(name = "descricao")
	private String descricao;

}