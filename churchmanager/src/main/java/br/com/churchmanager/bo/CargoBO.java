package br.com.churchmanager.bo;

import br.com.churchmanager.dao.CargoDAO;
import br.com.churchmanager.exception.DadosException;
import br.com.churchmanager.exception.NegocioException;
import br.com.churchmanager.exception.ViolacaoDeRestricaoException;
import br.com.churchmanager.generic.dao.Buscador;
import br.com.churchmanager.model.Cargo;
import br.com.churchmanager.model.filter.CargoFilter;
import br.com.churchmanager.util.MyLazyDataModel;

import java.io.Serializable;
import java.util.List;
import javax.inject.Inject;

public class CargoBO implements Serializable, Buscador<Cargo> {
	
	private static final long serialVersionUID = 322432123432121L;
	
	@Inject
	CargoDAO dao;

	public void salvar(Cargo cargo) throws NegocioException, ViolacaoDeRestricaoException, DadosException {
		this.validar(cargo);
		this.dao.salvar(cargo);
	}

	public void atualizar(Cargo cargo) throws NegocioException, ViolacaoDeRestricaoException, DadosException {
		this.validar(cargo);
		this.dao.atualizar(cargo);
	}

	public void deletar(Cargo cargo) {
		this.dao.excluir(cargo);
	}

	public List<Cargo> listar() {
		return this.dao.listar(true, new String[0]);
	}

	public void validar(Cargo cargo) throws NegocioException {
		
	}

	public Cargo buscarPorId(Serializable id) {
		return this.dao.buscarPorId(id);
	}

	public MyLazyDataModel<Cargo> filtrar(CargoFilter cargoFilter) {
		return this.dao.filtrar(this.dao, cargoFilter);
	}
}