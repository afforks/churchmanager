package br.com.churchmanager.model;

public enum EstadoPatrimonio {
	PESSIMO("Péssimo", "background: #ff3f30"), RUIM("Ruim", "background: #ff7600"), REGULAR("Regular",
			"background: #ffe007"), BOM("Bom", "background: #2196f3"), OTIMO("Ótimo", "background: #4caf50");

	private String descricao;
	private String cor;

	private EstadoPatrimonio(String descricao, String cor) {
		this.descricao = descricao;
		this.cor = cor;
	}

	public String getDescricao() {
		return this.descricao;
	}

	public String getCor() {
		return this.cor;
	}
}