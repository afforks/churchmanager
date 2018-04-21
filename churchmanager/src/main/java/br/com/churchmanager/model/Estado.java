package br.com.churchmanager.model;

public enum Estado {
	AC("Acre", "AC"), 
	AL("Alagoas", "AL"), 
	AP("Amapá", "AP"), 
	AM("Amazonas", "AM"), 
	BA("Bahia", "BA"), 
	CE("Ceará", "CE"), 
	DF("Distrito Federal", "DF"), 
	ES("Espírito Santo", "ES"), 
	GO("Goiás", "GO"), 
	MA("Maranhão", "MA"), 
	MT("Mato Grosso", "MT"), 
	MS("Mato Grosso do Sul", "MS"), 
	MG("Minas Gerais", "MG"), 
	PA("Pará","PA"), 
	PB("Paraíba", "PB"), 
	PR("Paraná", "PR"), 
	PE("Pernambuco", "PE"), 
	PI("Piauí", "PI"), 
	RJ("Rio de Janeiro", "RJ"), 
	RN("Rio Grande do Norte", "RN"), 
	RS("Rio Grande do Sul", "RS"), 
	RO("Rondônia", "RO"), 
	RR("Roraima", "RR"), 
	SC("Santa Catarina", "SC"), 
	SP("São Paulo", "SP"), 
	SE("Sergipe", "SE"), 
	TO("Tocantins", "TO"), 
	OUTRO("Outro", "OUT");

	private String descricao;
	private String abreviacao;

	private Estado(String descricao, String abreviacao) {
		this.descricao = descricao;
		this.abreviacao = abreviacao;
	}

	public String getDescricao() {
		return this.descricao;
	}

	public String getAbreviacao() {
		return this.abreviacao;
	}
}