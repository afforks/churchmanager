package br.com.churchmanager.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.Pattern;

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
@AllArgsConstructor
@ToString
@Builder
public class Endereco implements Serializable {
	private static final long serialVersionUID = 7606653748140748746L;

	@Column(name = "logradouro")
	private String logradouro;

	@Column(name = "referencia")
	private String referencia;

	@Column(name = "bairro")
	private String bairro;

	@Builder.Default
	@Column(name = "cep")
	@Pattern(regexp = "\\d{5}-\\d{3}", message = "Cep com formato inválido")
	private String cep = "62940-000";

	@Builder.Default
	@Column(name = "cidade")
	private String cidade = "Morada Nova";

	@Builder.Default
	@Column(name = "estado")
	@Enumerated(EnumType.STRING)
	private Estado estado = Estado.CE;

	@Builder.Default
	@Column(name = "pais")
	private String pais = "Brasil";
 
}