package br.com.churchmanager.model;

public enum TipoMovimentacao {
	
	ENTRADA("Entrada", "background: #4CAF50"), SAIDA("Saída", "background: #f44336");

	private String descricao;
	private String cor;

	private TipoMovimentacao(String descricao, String cor) {
		this.descricao = descricao;
		this.cor = cor;
	}

	public String getDescricao() {
		return this.descricao;
	}

	public String getCor() {
		return this.cor;
	}

	public static String tipoPorDescricao(String tipo) {
		return ENTRADA.toString().equals(tipo) ? ENTRADA.getDescricao() : SAIDA.getDescricao();
	}

	public static String backgroundPorDescricao(String tipo) {
		return ENTRADA.toString().equals(tipo) ? ENTRADA.getCor() : SAIDA.getCor();
	}
}