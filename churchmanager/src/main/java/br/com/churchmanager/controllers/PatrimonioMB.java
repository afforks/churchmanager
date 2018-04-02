package br.com.churchmanager.controllers;

import br.com.churchmanager.bo.PatrimonioBO;
import br.com.churchmanager.model.AvaliacaoPatrimonio;
import br.com.churchmanager.model.Patrimonio;
import br.com.churchmanager.model.Tipo;
import br.com.churchmanager.model.filter.PatrimonioFilter;
import br.com.churchmanager.util.BuscaObjeto;
import br.com.churchmanager.util.MyLazyDataModel;
import br.com.churchmanager.util.faces.FacesUtil;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import org.primefaces.context.RequestContext;

@Named
@ViewScoped
public class PatrimonioMB implements Serializable {
	private static final long serialVersionUID = 1L;
	private Patrimonio patrimonio;
	private AvaliacaoPatrimonio avaliacao;
	private List<Patrimonio> patrimonios;
	private MyLazyDataModel<Patrimonio> patrimoniosLazy;
	private PatrimonioFilter patrimonioFilter;
	@Inject
	private PatrimonioBO bo;

	@PostConstruct
	public void init() {
		Patrimonio patrimonio = (Patrimonio) BuscaObjeto.comParametroGET(Patrimonio.class, "id", this.bo);
		this.patrimonio = patrimonio;
	}

	public String salvar() {
		this.bo.salvar(this.patrimonio);
		FacesUtil.informacao("msg", "Cadastro com sucesso!", this.patrimonio.toString());
		FacesUtil.atualizaComponenteDeMensagem("msg");
		this.patrimonio = null;
		return null;
	}

	public String atualizar() {
		this.bo.atualizar(this.patrimonio);
		FacesUtil.informacao("msg", "Editado com sucesso!", this.patrimonio.toString());
		FacesUtil.atualizaComponenteDeMensagem("msg");
		FacesUtil.manterMensagem();
		this.patrimonio = null;
		return "/list/patrimonio?faces-redirect=true";
	}

	public String filtrar() {
		this.patrimoniosLazy = this.bo.filtrar(this.patrimonioFilter);
		return null;
	}

	public String deletar() {
		this.bo.deletar(this.patrimonio);
		this.patrimonio = null;
		return null;
	}

	public List<Patrimonio> patrimonios() {
		return this.bo.listar();
	}

	public String removerTipo() {
		this.patrimonio.setTipo((Tipo) null);
		FacesUtil.atencao("msg", "O tipo de patrimônio foi removido!", (String) null);
		return null;
	}

	public String adicionarAvaliacao() {
		this.patrimonio.adicionar(this.avaliacao);
		FacesUtil.informacao("avaliacao-msg", "Avaliação adicionada!", (String) null);
		this.avaliacao = new AvaliacaoPatrimonio();
		return this.resetarAvaliacao();
	}

	public String atualizarAvaliacao() {
		this.patrimonio.atualizar(this.avaliacao);
		FacesUtil.informacao("avaliacao-msg", "Avaliação atualzada!", (String) null);
		this.avaliacao = new AvaliacaoPatrimonio();
		RequestContext.getCurrentInstance().execute("PF(\'cad-avaliacao-patrimonio\').hide();");
		return this.resetarAvaliacao();
	}

	public String removerAvaliacao() {
		this.patrimonio.remover(this.avaliacao);
		FacesUtil.informacao("avaliacao-msg", "Avaliação removida!", (String) null);
		return this.resetarAvaliacao();
	}

	public String resetarAvaliacao() {
		this.avaliacao = null;
		return null;
	}

	public Patrimonio getPatrimonio() {
		if (this.patrimonio == null) {
			this.patrimonio = new Patrimonio();
		}

		return this.patrimonio;
	}

	public void setPatrimonio(Patrimonio patrimonio) {
		this.patrimonio = patrimonio;
	}

	public List<Patrimonio> getPatrimonios() {
		if (this.patrimonios == null) {
			this.patrimonios = new ArrayList<>();
		}

		return this.patrimonios;
	}

	public void setPatrimonios(List<Patrimonio> patrimonios) {
		this.patrimonios = patrimonios;
	}

	public MyLazyDataModel<Patrimonio> getPatrimoniosLazy() {
		if (this.patrimoniosLazy == null) {
			this.patrimoniosLazy = this.bo.filtrar(this.patrimonioFilter);
		}

		return this.patrimoniosLazy;
	}

	public void setPatrimoniosLazy(MyLazyDataModel<Patrimonio> patrimoniosLazy) {
		this.patrimoniosLazy = patrimoniosLazy;
	}

	public PatrimonioFilter getPatrimonioFilter() {
		if (this.patrimonioFilter == null) {
			this.patrimonioFilter = new PatrimonioFilter();
		}

		return this.patrimonioFilter;
	}

	public void setPatrimonioFilter(PatrimonioFilter patrimonioFilter) {
		this.patrimonioFilter = patrimonioFilter;
	}

	public AvaliacaoPatrimonio getAvaliacao() {
		if (this.avaliacao == null) {
			this.avaliacao = new AvaliacaoPatrimonio();
		}

		return this.avaliacao;
	}

	public void setAvaliacao(AvaliacaoPatrimonio avaliacao) {
		this.avaliacao = avaliacao;
	}
}