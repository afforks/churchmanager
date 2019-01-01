package br.com.churchmanager.controllers;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.deltaspike.jsf.api.message.JsfMessage;

import br.com.churchmanager.exception.DadosException;
import br.com.churchmanager.exception.NegocioException;
import br.com.churchmanager.exception.ViolacaoDeRestricaoException;
import br.com.churchmanager.jsf.Msgs;
import br.com.churchmanager.jsf.primefaces.LazyDataModel;
import br.com.churchmanager.model.Cargo;
import br.com.churchmanager.model.filter.CargoFilter;
import br.com.churchmanager.service.CargoService;

@Named
@ViewScoped
public class CargoMB implements Serializable {
	private static final long serialVersionUID = 1L;
	private Cargo cargo;
	private List<Cargo> cargos;
	private LazyDataModel<Cargo> cargosLazy;
	private CargoFilter cargoFilter;
	@Inject
	private CargoService bo;

	@Inject
	private JsfMessage<Msgs> msgs;

	@Inject
	private br.com.churchmanager.jsf.FacesUtil facesUtil;

	@PostConstruct
	public void init() {
		if (facesUtil.isNotPostback()) {
			Cargo cargo = null;
			this.cargo = cargo;
		}
	}

	public String salvar() {
		try {
			this.bo.save(this.cargo);
			msgs.addInfo().cadastradoComSucesso();
			this.cargo = null;
		} catch (NegocioException | ViolacaoDeRestricaoException | DadosException e) {
			msgs.addWarn().atencao("Atenção!", e.getMessage());
		} finally {
			facesUtil.atualizarComponente("msg");
		}
		return null;
	}

	public String atualizar() {
		try {
			this.bo.save(this.cargo);
			msgs.addInfo().editadoComSucesso();
		} catch (NegocioException | ViolacaoDeRestricaoException | DadosException e) {
			msgs.addWarn().atencao("Atenção!", e.getMessage());
		} finally {
			facesUtil.atualizarComponente("msg");
			facesUtil.manterMensagem();
			this.cargo = null;
		}
		return "/list/cargo?faces-redirect=true";
	}

	public String filtrar() {
		this.cargosLazy = this.bo.lazyList(this.cargoFilter);
		return null;
	}

	public String deletar() {
		this.bo.delete(this.cargo);
		this.cargo = null;
		return null;
	}

	public List<Cargo> cargos() {
		return this.bo.findAll();
	}

	public Cargo getCargo() {
		if (this.cargo == null) {
			this.cargo = new Cargo();
		}

		return this.cargo;
	}

	public void setCargo(Cargo cargo) {
		this.cargo = cargo;
	}

	public List<Cargo> getCargos() {
		if (this.cargos == null) {
			this.cargos = new ArrayList<>();
		}

		return this.cargos;
	}

	public void setCargos(List<Cargo> cargos) {
		this.cargos = cargos;
	}

	public LazyDataModel<Cargo> getCargosLazy() {
		if (this.cargosLazy == null) {
			this.cargosLazy = this.bo.lazyList(this.getCargoFilter());
		}

		return this.cargosLazy;
	}

	public void setCargosLazy(LazyDataModel<Cargo> cargosLazy) {
		this.cargosLazy = cargosLazy;
	}

	public CargoFilter getCargoFilter() {
		if (this.cargoFilter == null) {
			this.cargoFilter = new CargoFilter();
		}

		return this.cargoFilter;
	}

	public void setCargoFilter(CargoFilter cargoFilter) {
		this.cargoFilter = cargoFilter;
	}

}
