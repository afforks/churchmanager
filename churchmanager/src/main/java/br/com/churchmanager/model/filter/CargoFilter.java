package br.com.churchmanager.model.filter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Restrictions;

import br.com.churchmanager.generic.dao.Alias;

public class CargoFilter implements Filter, Serializable {
	private static final long serialVersionUID = 11043712329577L;
	private String nome;

	public List<Criterion> restricoes() {
		ArrayList<Criterion> restricoes = new ArrayList<>();
		if (StringUtils.isNotBlank(this.getNome())) {
			restricoes.add(Restrictions.like("nome", this.getNome(), MatchMode.ANYWHERE));
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
}