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

@Entity(name = "pagina")
@Table(name = "pagina")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode(callSuper = true)
@Builder
public class Pagina extends EntidadeGenerica implements Serializable {

	private static final long serialVersionUID = 1L;

	@Column(name = "nome", nullable = false, unique = true)
	private String nome;

	@Column(name = "nome_identificador", nullable = false, unique = true)
	private String nomeIdentificador;

	@Column(name = "descricao")
	private String descricao;

	public void setNome(String nome) {
		this.nome = nome;
		this.setNomeIdentificador(nome.toUpperCase().replaceAll(" ", "_").replaceAll("[ÁÀÂÃ]", "A")
				.replaceAll("[ÉÈÊ]", "E").replaceAll("[ÍÏ]", "I").replaceAll("[ÓÔÕÖ]", "O").replaceAll("Ú", "U")
				.replaceAll("Ç", "C").replaceAll("Ñ", "N"));
	}

}