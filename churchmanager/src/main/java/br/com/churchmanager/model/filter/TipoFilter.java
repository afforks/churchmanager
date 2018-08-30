package br.com.churchmanager.model.filter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Restrictions;

import br.com.churchmanager.dao.generic.Alias;
import br.com.churchmanager.model.Status;

public class TipoFilter implements Filter, Serializable {
	private static final long serialVersionUID = 1232701463701329577L;
	private String nome;

	public List<Criterion> restricoes() {
		ArrayList<Criterion> restricoes = new ArrayList<>();
		if (StringUtils.isNotBlank(this.getNome())) {
			restricoes.add(Restrictions.like("nome", this.getNome(), MatchMode.ANYWHERE));
		}
		restricoes.add(Restrictions.eq("status", Status.ATIVO));
		return restricoes;
	}

	public List<Projection> projecoes() {
		return new ArrayList<>();
	}

	public List<Alias> aliases() {
		return new ArrayList<>();
	}

	public boolean usarDistinct() {
		return false;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

}