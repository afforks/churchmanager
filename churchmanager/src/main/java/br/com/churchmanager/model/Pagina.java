package br.com.churchmanager.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity(name = "pagina")
@Table(name = "pagina")
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
		this.setNomeIdentificador(nome.toUpperCase().replaceAll(" ", "_").replaceAll("[ÁÀÂÃ]", "A")
				.replaceAll("[ÉÈÊ]", "E").replaceAll("[ÍÏ]", "I").replaceAll("[ÓÔÕÖ]", "O").replaceAll("Ú", "U")
				.replaceAll("Ç", "C").replaceAll("Ñ", "N"));
	}

	public String getNomeIdentificador() {
		return nomeIdentificador;
	}

	public void setNomeIdentificador(String nomeIdentificador) {
		this.nomeIdentificador = nomeIdentificador;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getNome() {
		return nome;
	}

}