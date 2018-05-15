package br.com.churchmanager.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum EstadoPatrimonio {
	
	PESSIMO("Péssimo", "background: #ff3f30"), 
	RUIM("Ruim", "background: #ff7600"), 
	REGULAR("Regular", "background: #ffe007"), 
	BOM("Bom", "background: #2196f3"), 
	OTIMO("Ótimo", "background: #4caf50");

	private String descricao;
	private String cor;

}