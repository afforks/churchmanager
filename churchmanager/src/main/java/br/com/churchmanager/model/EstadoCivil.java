package br.com.churchmanager.model;

public enum EstadoCivil {

	SOLTEIRO("Solteiro"), 
	CASADO("Casado"), 
	VIUVO("Viúvo"), 
	DIVORCIADO("Divorciado"), 
	OUTROS("Outros");

	private EstadoCivil(String descricao) {
		this.descricao = descricao;
	}

	private String descricao;
	
	public String getDescricao() {
		return descricao;
	}

}