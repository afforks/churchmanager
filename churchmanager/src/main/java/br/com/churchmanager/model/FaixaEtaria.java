package br.com.churchmanager.model;

public class FaixaEtaria {
	private static final String CRIANCAS = "Crianças";
	private static final String PRE_ADOLESCENTES = "Pré-adolescentes";
	private static final String ADOLESCENTES = "Adolescentes";
	private static final String ADULTOS = "Adultos";
	private static final String IDOSOS = "Idosos";

	public static String faixaEtaria(int idade) {
		return idade <= 7
				? "Crianças"
				: (idade >= 8 && idade <= 14
						? "Pré-adolescentes"
						: (idade >= 15 && idade <= 19
								? "Adolescentes"
								: (idade >= 20 && idade <= 40 ? "Adultos" : "Idosos")));
	}
}