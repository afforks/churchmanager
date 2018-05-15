package br.com.churchmanager.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum EstadoCivil {

	SOLTEIRO("Solteiro"), 
	CASADO("Casado"), 
	VIUVO("Viúvo"), 
	DIVORCIADO("Divorciado"), 
	OUTROS("Outros");

	private String descricao;

}