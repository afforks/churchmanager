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
import br.com.churchmanager.jsf.FacesUtil;
import br.com.churchmanager.jsf.Msgs;
import br.com.churchmanager.jsf.primefaces.LazyDataModel;
import br.com.churchmanager.model.AvaliacaoPatrimonio;
import br.com.churchmanager.model.Patrimonio;
import br.com.churchmanager.model.filter.PatrimonioFilter;
import br.com.churchmanager.service.PatrimonioService;

@Named
@ViewScoped
public class PatrimonioMB implements Serializable {
	private static final long serialVersionUID = 1L;
	private Patrimonio patrimonio;
	private AvaliacaoPatrimonio avaliacao;
	private List<Patrimonio> patrimonios;
	private LazyDataModel<Patrimonio> patrimoniosLazy;
	private PatrimonioFilter patrimonioFilter;
	@Inject
	private PatrimonioService service;
	@Inject
	private FacesUtil facesUtil;
	@Inject
	private JsfMessage<Msgs> msgs;

	@PostConstruct
	public void init() {
		Patrimonio p = null;
		this.patrimonio = p;
	}

	public String salvar() {
		try {
			this.service.save(this.patrimonio);
			msgs.addInfo().cadastradoComSucesso();
			this.patrimonio = null;
		} catch (NegocioException | ViolacaoDeRestricaoException | DadosException e) {
			msgs.addWarn().atencao("Atenção!", e.getMessage());
		} finally {
			facesUtil.atualizarComponente("msg");
		}
		return null;
	}

	public String atualizar() {

		try {
			this.service.save(this.patrimonio);
			msgs.addInfo().editadoComSucesso();
			this.patrimonio = null;
		} catch (NegocioException | ViolacaoDeRestricaoException | DadosException e) {
			msgs.addWarn().atencao("Atenção!", e.getMessage());
			return null;
		} finally {
			facesUtil.atualizarComponente("msg");
			facesUtil.manterMensagem();
		}
		return "/list/patrimonio?faces-redirect=true";
	}

	public String filtrar() {
		this.patrimoniosLazy = this.service.lazyList(this.patrimonioFilter);
		return null;
	}

	public String deletar() {
		this.service.delete(this.patrimonio);
		this.patrimonio = null;
		return null;
	}

	public List<Patrimonio> patrimonios() {
		return this.service.findAll();
	}

	public String removerTipo() {
		this.patrimonio.setTipo(null);
		msgs.addWarn().atencao("O tipo de patrimônio foi removido!", "");
		return null;
	}

	public String adicionarAvaliacao() {
		this.patrimonio.adicionar(this.avaliacao);
		msgs.addInfo().info("Avaliação adicionada!", "");
		facesUtil.atualizarComponente("avaliacao-msg");
		this.avaliacao = new AvaliacaoPatrimonio();
		return this.resetarAvaliacao();
	}

	public String atualizarAvaliacao() {
		this.patrimonio.update(this.avaliacao);
		msgs.addInfo().info("Avaliação atualzada!", "");
		this.avaliacao = new AvaliacaoPatrimonio();
		facesUtil.atualizarComponente("avaliacao-msg");
		facesUtil.executarJavascript("PF(\'cad-avaliacao-patrimonio\').hide();");
		return this.resetarAvaliacao();
	}

	public String removerAvaliacao() {
		this.patrimonio.remover(this.avaliacao);
		msgs.addInfo().info("Avaliação removida!", "");
		facesUtil.atualizarComponente("avaliacao-msg");
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

	public LazyDataModel<Patrimonio> getPatrimoniosLazy() {
		if (this.patrimoniosLazy == null) {
			this.patrimoniosLazy = this.service.lazyList(this.patrimonioFilter);
		}

		return this.patrimoniosLazy;
	}

	public void setPatrimoniosLazy(LazyDataModel<Patrimonio> patrimoniosLazy) {
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