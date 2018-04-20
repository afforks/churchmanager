package br.com.churchmanager.controllers;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.churchmanager.bo.AtividadeEclesiasticaBO;
import br.com.churchmanager.exception.DadosException;
import br.com.churchmanager.exception.NegocioException;
import br.com.churchmanager.exception.ViolacaoDeRestricaoException;
import br.com.churchmanager.model.AtividadeEclesiastica;
import br.com.churchmanager.model.Status;
import br.com.churchmanager.model.filter.AtividadeEclesiasticaFilter;
import br.com.churchmanager.util.BuscaObjeto;
import br.com.churchmanager.util.MyLazyDataModel;
import br.com.churchmanager.util.faces.FacesUtil;

@Named
@ViewScoped
public class AtividadeEclesiasticaMB implements Serializable {
	private static final long serialVersionUID = 1L;
	private AtividadeEclesiastica pagina;
	private List<AtividadeEclesiastica> paginas;
	private MyLazyDataModel<AtividadeEclesiastica> paginasLazy;
	private AtividadeEclesiasticaFilter paginaFilter;
	@Inject
	private AtividadeEclesiasticaBO bo;

	@PostConstruct
	public void init() {
		AtividadeEclesiastica pagina = (AtividadeEclesiastica) BuscaObjeto.comParametroGET(AtividadeEclesiastica.class,
				"id", this.bo);
		this.pagina = pagina;
	}

	public String salvar() {
		try {
			this.bo.salvar(this.pagina);
			FacesUtil.informacao("msg", "Cadastrado com sucesso!", this.pagina.toString());
			this.pagina = null;
		} catch (NegocioException e) {
			FacesUtil.atencao("msg", "Atenção!", e.getMessage());
			e.printStackTrace();
		} catch (ViolacaoDeRestricaoException e) {
			FacesUtil.atencao("msg", "Atenção!", "O nome '"+pagina.getNome()+"' está duplicado, por favor, informe outro!");
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
			this.bo.atualizar(this.pagina);
			FacesUtil.informacao("msg", "Editado com sucesso!", this.pagina.toString());
			this.pagina = null;
		} catch (NegocioException e) {
			FacesUtil.atencao("msg", "Atenção!", e.getMessage());
			e.printStackTrace();
			return null;
		} catch (ViolacaoDeRestricaoException e) {
			FacesUtil.atencao("msg", "Atenção!", "O nome '"+pagina.getNome()+"' está duplicado, por favor, informe outro!");
			e.printStackTrace();
			return null;
		} catch (DadosException e) {
			FacesUtil.atencao("msg", "Atenção!", e.getMessage());
			e.printStackTrace();
			return null;
		} finally {
			FacesUtil.atualizaComponente("msg");
			FacesUtil.manterMensagem();
		}
		return "/list/pagina?faces-redirect=true";
	}

	public String filtrar() {
		this.paginasLazy = this.bo.filtrar(this.paginaFilter);
		return null;
	}

	public String deletar() {
		this.bo.deletar(this.pagina);
		this.pagina = null;
		return null;
	}

	public Status[] listarStatus() {
		return Status.values();
	}

	public List<AtividadeEclesiastica> atividades() {
		return this.bo.listar();
	}

	public AtividadeEclesiastica getAtividadeEclesiastica() {
		if (this.pagina == null) {
			this.pagina = new AtividadeEclesiastica();
		}

		return this.pagina;
	}

	public void setAtividadeEclesiastica(AtividadeEclesiastica pagina) {
		this.pagina = pagina;
	}

	public List<AtividadeEclesiastica> getAtividadeEclesiasticas() {
		if (this.paginas == null) {
			this.paginas = new ArrayList<>();
		}

		return this.paginas;
	}

	public void setAtividadeEclesiasticas(List<AtividadeEclesiastica> paginas) {
		this.paginas = paginas;
	}

	public MyLazyDataModel<AtividadeEclesiastica> getAtividadeEclesiasticasLazy() {
		if (this.paginasLazy == null) {
			this.paginasLazy = this.bo.filtrar(this.paginaFilter);
		}

		return this.paginasLazy;
	}

	public void setAtividadeEclesiasticasLazy(MyLazyDataModel<AtividadeEclesiastica> paginasLazy) {
		this.paginasLazy = paginasLazy;
	}

	public AtividadeEclesiasticaFilter getAtividadeEclesiasticaFilter() {
		if (this.paginaFilter == null) {
			this.paginaFilter = new AtividadeEclesiasticaFilter();
		}

		return this.paginaFilter;
	}

	public void setAtividadeEclesiasticaFilter(AtividadeEclesiasticaFilter paginaFilter) {
		this.paginaFilter = paginaFilter;
	}
}