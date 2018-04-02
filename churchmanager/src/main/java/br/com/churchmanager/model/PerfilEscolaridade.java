package br.com.churchmanager.model;

public enum PerfilEscolaridade {
	SEM_ESCOLARIDADE("Sem Escolaridade"), FUNDAMENTAL_INCOMPLETO("Fundamental Incompleto"), FUNDAMENTAL_COMPLETO(
			"Fundamental Completo"), MEDIO_INCOMPLETO("Médio Incompleto"), MEDIO_COMPLETO(
					"Médio Completo"), SUPERIOR_INCOMPLETO(
							"Superior Incompleto"), SUPERIOR_COMPLETO("Superior Completo");

	private String descricao;

	private PerfilEscolaridade(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return this.descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
}