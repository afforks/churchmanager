package br.com.churchmanager.model.filter;

import br.com.churchmanager.generic.dao.Alias;
import br.com.churchmanager.model.filter.Filter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Restrictions;

public class PessoaCargoFilter implements Filter, Serializable {
	private static final long serialVersionUID = 5043712329577L;
	private String nomePessoa;
	private String nomeCargo;

	public List<Criterion> restricoes() {
		ArrayList<Criterion> restricoes = new ArrayList<>();
		if (StringUtils.isNotBlank(this.getNomePessoa())) {
			restricoes.add(Restrictions.like("pessoa.nome", this.getNomePessoa(), MatchMode.ANYWHERE));
		}

		if (StringUtils.isNotBlank(this.getNomeCargo())) {
			restricoes.add(Restrictions.like("cargo.nome", this.getNomeCargo(), MatchMode.ANYWHERE));
		}

		return restricoes;
	}

	public List<Projection> projecoes() {
		return null;
	}

	public List<Alias> aliases() {
		ArrayList<Alias> aliases = new ArrayList<>();
		aliases.add(new Alias("pessoa", "pessoa"));
		aliases.add(new Alias("cargo", "cargo"));
		return aliases;
	}

	public Boolean usarDistinct() {
		return null;
	}

	public String getNomePessoa() {
		return this.nomePessoa;
	}

	public void setNomePessoa(String nomePessoa) {
		this.nomePessoa = nomePessoa;
	}

	public String getNomeCargo() {
		return this.nomeCargo;
	}

	public void setNomeCargo(String nomeCargo) {
		this.nomeCargo = nomeCargo;
	}
}