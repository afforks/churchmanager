package br.com.churchmanager.model;

public enum FaixaEtaria {

	CRIANCAS("Crianças"), ADOLESCENTES("Adolescentes"), ADULTOS("Adultos"), IDOSOS("Idosos");

	private FaixaEtaria(String descricao) {
		this.descricao = descricao;
	}

	private String descricao;

	public String getDescricao() {
		return descricao;
	}

	/**
	 * conforme LEI Nº 10.741, Art. 1o, DE 01 DE OUTUBRO DE 2003 (Estatuto do idoso)
	 * conforme LEI Nº 8.069, Art. 2º, DE 13 DE JULHO DE 1990 (Estatuto da Criança e
	 * do Adolescente)
	 */
	public static FaixaEtaria porIdade(int idade) {
		if (idade <= 12)
			return CRIANCAS;
		else if (idade >= 13 && idade <= 18)
			return ADOLESCENTES;
		else if (idade >= 19 && idade <= 59)
			return ADULTOS;
		else
			return IDOSOS;
	}

}
