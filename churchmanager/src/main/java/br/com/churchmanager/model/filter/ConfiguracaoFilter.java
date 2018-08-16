package br.com.churchmanager.model.filter;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Projection;

import br.com.churchmanager.dao.generic.Alias;

public class ConfiguracaoFilter implements Filter {

	@Override
	public List<Criterion> restricoes() {
		return new ArrayList<>();
	}

	@Override
	public List<Projection> projecoes() {
		return new ArrayList<>();
	}

	@Override
	public List<Alias> aliases() {
		return new ArrayList<>();
	}

	@Override
	public boolean usarDistinct() {
		return true;
	}

}
