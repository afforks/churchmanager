package br.com.churchmanager.model;

public enum StatusMovimentacao {
	
	PAGO("Pago", "background: #2196F3"), 
	EM_ABERTO("Em aberto", "background: #FFEB3B");

	private StatusMovimentacao(String descricao, String cor) {
		this.descricao = descricao;
		this.cor = cor;
	}

	private String descricao;
	private String cor;

	public static String statusPorDescricao(String status) {
		return PAGO.toString().equals(status) ? PAGO.getDescricao() : EM_ABERTO.getDescricao();
	}

	public static String corPorDescricao(String status) {
		return PAGO.toString().equals(status) ? PAGO.getCor() : EM_ABERTO.getCor();
	}

	public String getDescricao() {
		return descricao;
	}

	public String getCor() {
		return cor;
	}
	
	
}