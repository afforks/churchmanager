package br.com.churchmanager.model;

public enum Status {
	ATIVO("Ativo"), INATIVO("Inativo");

	private String descricao;

	private Status(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return this.descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public static Status negarStatus(Status status) {
		return ATIVO.equals(status) ? INATIVO : ATIVO;
	}
}