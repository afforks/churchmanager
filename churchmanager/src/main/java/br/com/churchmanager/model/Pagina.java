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

@Entity(name = "pagina")
@Table(name = "pagina")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(of = { "nome" })
@EqualsAndHashCode(of= {"id"}, callSuper=true)
@Builder
public class Pagina extends EntidadeGenerica implements Serializable {

	private static final long serialVersionUID = 1L;

	@NotNull
	@Size(min = 3, max = 50)
	@Column(name = "nome", nullable = false, unique = true)
	private String nome;
	
	@NotNull
	@Size(min = 3, max = 50)
	@Column(name = "nome_identificador", nullable = false, unique = true)
	private String nomeIdentificador;

	@Size(max = 250)
	@Column(name = "descricao")
	private String descricao;

	public void setNome(String nome) {
		this.nome = nome;
		this.setNomeIdentificador(nome.toUpperCase()
				.replaceAll(" ", "_")
				.replaceAll("[ÁÀÂÃ]", "A")
				.replaceAll("[ÉÈÊ]", "E")
				.replaceAll("[ÍÏ]", "I")
				.replaceAll("[ÓÔÕÖ]", "O")
				.replaceAll("Ú", "U")
				.replaceAll("Ç", "C")
				.replaceAll("Ñ", "N"));
	}
}