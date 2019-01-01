package br.com.churchmanager.service;

import br.com.churchmanager.model.Dizimo;
import br.com.churchmanager.model.dto.PercentualDizimista;
import br.com.churchmanager.model.filter.DizimoFilter;

public interface DizimoService extends Service<Dizimo> {

	PercentualDizimista percentualDizimista(DizimoFilter filter);

}
