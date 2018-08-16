package br.com.churchmanager.model.filter;

import br.com.churchmanager.dao.generic.Alias;
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
public class UsuarioFilter implements Filter, Serializable {
	
	private static final long serialVersionUID = 5043701463701329577L;
	
	private String nome;
	private String nomeIdentificador;

	public List<Criterion> restricoes() {
		ArrayList<Criterion> restricoes = new ArrayList<>();
		if (StringUtils.isNotBlank(this.getNome())) {
			restricoes.add(Restrictions.like("nomeCompleto", this.getNome(), MatchMode.ANYWHERE));
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