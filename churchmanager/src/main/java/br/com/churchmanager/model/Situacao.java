package br.com.churchmanager.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Situacao {
	QUITADO("Quitado", "background: #49afcd"), NAO_QUITADO("Não quitado", "background: #da4f49");

	private String descricao;
	private String cor;

}