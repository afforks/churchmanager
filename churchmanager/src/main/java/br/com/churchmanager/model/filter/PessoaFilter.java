package br.com.churchmanager.model.filter;

import br.com.churchmanager.dao.generic.Alias;
import br.com.churchmanager.model.Sexo;
import br.com.churchmanager.model.Status;
import br.com.churchmanager.model.filter.Filter;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Restrictions;

@Getter
@Setter
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
}