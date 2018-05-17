package br.com.churchmanager.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum FormaMovimentacao {
	
	EM_ESPECIE("Em espécie", "background: #4CAF50"), 
	EM_CONTA("Em conta", "background: #673AB7");

	private String descricao;
	private String cor;

}