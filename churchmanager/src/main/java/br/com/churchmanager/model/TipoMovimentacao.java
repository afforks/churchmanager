package br.com.churchmanager.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TipoMovimentacao {
	
	ENTRADA("Entrada", "background: #4CAF50"), SAIDA("Saída", "background: #f44336");

	private String descricao;
	private String cor;

	public static String tipoPorDescricao(String tipo) {
		return ENTRADA.toString().equals(tipo) ? ENTRADA.getDescricao() : SAIDA.getDescricao();
	}

	public static String backgroundPorDescricao(String tipo) {
		return ENTRADA.toString().equals(tipo) ? ENTRADA.getCor() : SAIDA.getCor();
	}
}