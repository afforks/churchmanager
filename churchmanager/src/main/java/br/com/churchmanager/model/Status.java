package br.com.churchmanager.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Status {
	ATIVO("Ativo"), INATIVO("Inativo");

	private String descricao;

	public static Status negarStatus(Status status) {
		return ATIVO.equals(status) ? INATIVO : ATIVO;
	}
}