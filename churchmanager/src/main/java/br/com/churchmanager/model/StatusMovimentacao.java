package br.com.churchmanager.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum StatusMovimentacao {
	PAGO("Pago", "background: #2196F3"), EM_ABERTO("Em aberto", "background: #FFEB3B");

	private String descricao;
	private String cor;

	public static String statusPorDescricao(String status) {
		return PAGO.toString().equals(status) ? PAGO.getDescricao() : EM_ABERTO.getDescricao();
	}

	public static String corPorDescricao(String status) {
		return PAGO.toString().equals(status) ? PAGO.getCor() : EM_ABERTO.getCor();
	}
}