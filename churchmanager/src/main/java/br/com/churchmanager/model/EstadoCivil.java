package br.com.churchmanager.model;

public enum EstadoCivil {
	SOLTEIRO("Solteiro"), CASADO("Casado"), VIUVO("Viúvo"), DIVORCIADO("Divorciado"), OUTROS("Outros");

	private String descricao;

	private EstadoCivil(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return this.descricao;
	}
}