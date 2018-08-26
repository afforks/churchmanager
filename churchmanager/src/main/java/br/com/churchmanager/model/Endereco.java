package br.com.churchmanager.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import br.com.churchmanager.util.custom.Cep;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@ToString
@AllArgsConstructor
@Builder
public class Endereco implements Serializable {
	private static final long serialVersionUID = 7606653748140748746L;

	@Column(name = "logradouro")
	private String logradouro;

	@Column(name = "referencia")
	private String referencia;

	@Column(name = "bairro")
	private String bairro;

	@Column(name = "cep")
	@Cep
	private String cep = "62.940-000";

	@Column(name = "cidade")
	private String cidade = "Morada Nova";

	@Column(name = "estado")
	@Enumerated(EnumType.STRING)
	private Estado estado = Estado.CE;

	@Column(name = "pais")
	private String pais = "Brasil";

}