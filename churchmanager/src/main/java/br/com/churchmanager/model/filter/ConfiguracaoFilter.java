package br.com.churchmanager.model.filter;

import java.util.List;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Projection;

import br.com.churchmanager.dao.generic.Alias;

public class ConfiguracaoFilter implements Filter {

	@Override
	public List<Criterion> restricoes() {
		return null;
	}

	@Override
	public List<Projection> projecoes() {
		return null;
	}

	@Override
	public List<Alias> aliases() {
		return null;
	}

	@Override
	public Boolean usarDistinct() {
		return true;
	}

}
