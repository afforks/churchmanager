package br.com.churchmanager.model;

public enum PerfilEscolaridade {
	
	SEM_ESCOLARIDADE("Sem Escolaridade"), 
	FUNDAMENTAL_INCOMPLETO("Fundamental Incompleto"), 
	FUNDAMENTAL_COMPLETO("Fundamental Completo"), 
	MEDIO_INCOMPLETO("Médio Incompleto"), 
	MEDIO_COMPLETO("Médio Completo"), 
	TECNICO_INCOMPLETO("Técnico Incompleto"), 
	TECNICO_COMPLETO("Técnico Completo"), 
	TECNOLOGO_INCOMPLETO("Superior Incompleto"), 
	TECNOLOGO_COMPLETO("Tecnólogo Completo"),
	SUPERIOR_INCOMPLETO("Tecnólogo Incompleto"), 
	SUPERIOR_COMPLETO("Superior Completo");

	private PerfilEscolaridade(String descricao) {
		this.descricao = descricao;
	}

	private String descricao;

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

}