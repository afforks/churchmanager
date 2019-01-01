package br.com.churchmanager.util;

public enum Meses {
	JAN("Janeiro", "JAN", "01"), 
	FEV("Fevereiro", "FEV", "02"), 
	MAR("Março", "MAR", "03"), 
	ABR("Abril", "ABR", "04"), 
	MAI("Maio", "MAI", "05"), 
	JUN("Junho", "JUN", "06"), 
	JUL("Julho", "JUL", "07"), 
	AGO("Agosto", "AGO", "08"), 
	SET("Setembro", "SET", "09"), 
	OUT("Outubro", "OUT", "10"), 
	NOV("Novembro", "NOV", "11"), 
	DEZ("Dezembro", "DEZ", "12");

	private String nome;
	private String abreviacao;
	private String numero;

	private Meses(String nome, String abreviacao, String numero) {
		this.nome = nome;
		this.abreviacao = abreviacao;
		this.numero = numero;
	}

	public String getNome() {
		return this.nome;
	}

	public String getNumero() {
		return this.numero;
	}

	public String getAbreviacao() {
		return this.abreviacao;
	}

	public static Meses mesPorNome(String mes) {
		Meses mesAux = null;

		for (int i = 0; i < values().length; ++i) {
			if (values()[i].getNome().equals(mes)) {
				mesAux = values()[i];
				break;
			}
		}

		return mesAux;
	}

	public static Meses mesPorNumero(String mes) {
		Meses mesAux = null;

		for (int i = 0; i < values().length; ++i) {
			if (values()[i].getNumero().equals(mes)) {
				mesAux = values()[i];
				break;
			}
		}

		return mesAux;
	}
}