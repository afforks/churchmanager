package br.com.churchmanager.controllers;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.churchmanager.bo.CargoBO;
import br.com.churchmanager.exception.DadosException;
import br.com.churchmanager.exception.NegocioException;
import br.com.churchmanager.exception.ViolacaoDeRestricaoException;
import br.com.churchmanager.model.Cargo;
import br.com.churchmanager.model.Status;
import br.com.churchmanager.model.filter.CargoFilter;
import br.com.churchmanager.util.BuscaObjeto;
import br.com.churchmanager.util.MyLazyDataModel;
import br.com.churchmanager.util.faces.FacesUtil;

@Named
@ViewScoped
public class CargoMB implements Serializable {
	private static final long serialVersionUID = 1L;
	private Cargo cargo;
	private List<Cargo> cargos;
	private MyLazyDataModel<Cargo> cargosLazy;
	private CargoFilter cargoFilter;
	@Inject
	private CargoBO bo;

	@PostConstruct
	public void init() {
		if(FacesUtil.isNotPostback()) {
			Cargo cargo = (Cargo) BuscaObjeto.comParametroGET(Cargo.class, "id", this.bo);
			this.cargo = cargo;
		}
	}

	public String salvar() {
		try {	
			this.bo.salvar(this.cargo);
			FacesUtil.informacao("msg", "Cadastro com sucesso!", this.cargo.toString());
			this.cargo = null;
		} catch (NegocioException e) {
			FacesUtil.atencao("msg", "Atenção!", e.getMessage());
			e.printStackTrace();
		} catch (ViolacaoDeRestricaoException e) {
			FacesUtil.atencao("msg", "Atenção!", e.getMessage());
			e.printStackTrace();
		} catch (DadosException e) {
			FacesUtil.atencao("msg", "Atenção!", e.getMessage());
			e.printStackTrace();
		} finally {
			FacesUtil.atualizaComponente("msg");
		}
		return null;
	}

	public String atualizar() {
		try {
			this.bo.atualizar(this.cargo);
			FacesUtil.informacao("msg", "Editado com sucesso!", this.cargo.toString());
			FacesUtil.atualizaComponente("msg");
			FacesUtil.manterMensagem();
			this.cargo = null;
		
		} catch (ViolacaoDeRestricaoException | DadosException | NegocioException e) {
			FacesUtil.atencao("msg", "Atenção!", e.getMessage());
			e.printStackTrace();
			return null;
		} finally {
			FacesUtil.atualizaComponente("msg");
		}
	 
		return "/list/cargo?faces-redirect=true";
	}

	public String filtrar() {
		this.cargosLazy = this.bo.filtrar(this.cargoFilter);
		return null;
	}

	public String deletar() {
		this.bo.deletar(this.cargo);
		this.cargo = null;
		return null;
	}

	public Status[] listarStatus() {
		return Status.values();
	}

	public List<Cargo> cargos() {
		return this.bo.listar();
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

	public MyLazyDataModel<Cargo> getCargosLazy() {
		if (this.cargosLazy == null) {
			this.cargosLazy = this.bo.filtrar(this.getCargoFilter());
		}

		return this.cargosLazy;
	}

	public void setCargosLazy(MyLazyDataModel<Cargo> cargosLazy) {
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
