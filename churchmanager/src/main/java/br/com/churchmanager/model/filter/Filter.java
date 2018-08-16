package br.com.churchmanager.model.filter;

import java.util.List;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Projection;

import br.com.churchmanager.dao.generic.Alias;

public interface Filter {
	List<Criterion> restricoes();

	List<Projection> projecoes();

	List<Alias> aliases();

	boolean usarDistinct();
}