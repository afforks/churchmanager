package br.com.churchmanager.model.filter;

import br.com.churchmanager.generic.dao.Alias;
import br.com.churchmanager.model.Sexo;
import br.com.churchmanager.model.filter.Filter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Restrictions;

public class PessoaFilter implements Filter, Serializable {
	private static final long serialVersionUID = 5043742463701329577L;
	private String nome;
	private String mes;
	private String ano;
	private Sexo sexo;

	public List<Criterion> restricoes() {
		ArrayList<Criterion> restricoes = new ArrayList<>();
		if (StringUtils.isNotBlank(this.getNome())) {
			restricoes.add(Restrictions.like("nome", this.getNome(), MatchMode.ANYWHERE));
		}

		if (this.sexo != null) {
			restricoes.add(Restrictions.eq("sexo", this.getSexo()));
		}

		return restricoes;
	}

	public List<Projection> projecoes() {
		return null;
	}

	public List<Alias> aliases() {
		return null;
	}

	public Boolean usarDistinct() {
		return null;
	}

	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getMes() {
		return this.mes;
	}

	public void setMes(String mes) {
		this.mes = mes;
	}

	public String getAno() {
		return this.ano;
	}

	public void setAno(String ano) {
		this.ano = ano;
	}

	public Sexo getSexo() {
		return this.sexo;
	}

	public void setSexo(Sexo sexo) {
		this.sexo = sexo;
	}
}