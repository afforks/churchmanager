package br.com.churchmanager.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import br.com.churchmanager.model.EntidadeGenerica;

@Entity(name = "pagina")
@Table(name = "pagina")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Pagina extends EntidadeGenerica implements Serializable {
	private static final long serialVersionUID = 1L;
	@Column(name = "nome", nullable = false, unique = true)
	private String nome;
	@Column(name = "nome_identificador", nullable = false, unique = true)
	private String nomeIdentificador;
	@Column(name = "descricao")
	private String descricao;

	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
		this.setNomeIdentificador(nome.toUpperCase().replaceAll(" ", "_").replaceAll("[ÁÀÂÃ]", "A")
				.replaceAll("[ÉÈÊ]", "E").replaceAll("[ÍÏ]", "I").replaceAll("[ÓÔÕÖ]", "O").replaceAll("Ú", "U")
				.replaceAll("Ç", "C").replaceAll("Ñ", "N"));
	}

	public String getNomeIdentificador() {
		return this.nomeIdentificador;
	}

	private void setNomeIdentificador(String nomeIdentificador) {
		this.nomeIdentificador = nomeIdentificador;
	}

	public String getDescricao() {
		return this.descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String toString() {
		return "{Nome= " + this.nome + ", Nome Identificador= " + this.nomeIdentificador + ", Descrição= "
				+ this.descricao + "}";
	}
}