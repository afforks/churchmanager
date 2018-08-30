package br.com.churchmanager.model;

public enum TipoMovimentacao {

	ENTRADA("Entrada", "background: #4CAF50"), 
	SAIDA("Saída", "background: #f44336");
	
	private TipoMovimentacao(String descricao, String cor) {
		this.descricao = descricao;
		this.cor = cor;
	}

	private String descricao;
	private String cor;

	public static String tipoPorDescricao(String tipo) {
		return ENTRADA.toString().equals(tipo) ? ENTRADA.getDescricao() : SAIDA.getDescricao();
	}

	public static String backgroundPorDescricao(String tipo) {
		return ENTRADA.toString().equals(tipo) ? ENTRADA.getCor() : SAIDA.getCor();
	}

	public String getDescricao() {
		return descricao;
	}

	public String getCor() {
		return cor;
	}

}