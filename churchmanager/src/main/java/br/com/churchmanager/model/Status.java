package br.com.churchmanager.model;

public enum Status {
	ATIVO("Ativo"), INATIVO("Inativo");

	private Status(String descricao) {
		this.descricao = descricao;
	}

	private String descricao;

	public static Status negarStatus(Status status) {
		return ATIVO.equals(status) ? INATIVO : ATIVO;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

}