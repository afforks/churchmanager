package br.com.churchmanager.model;

public enum StatusMovimentacao {
	PAGO("Pago", "background: #2196F3"), EM_ABERTO("Em aberto", "background: #FFEB3B");

	private String descricao;
	private String cor;

	private StatusMovimentacao(String descricao, String cor) {
		this.descricao = descricao;
		this.cor = cor;
	}

	public String getDescricao() {
		return this.descricao;
	}

	public String getCor() {
		return this.cor;
	}

	public void setCor(String cor) {
		this.cor = cor;
	}

	public static String statusPorDescricao(String status) {
		return PAGO.toString().equals(status) ? PAGO.getDescricao() : EM_ABERTO.getDescricao();
	}

	public static String corPorDescricao(String status) {
		return PAGO.toString().equals(status) ? PAGO.getCor() : EM_ABERTO.getCor();
	}
}