package br.com.churchmanager.controllers;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.apache.deltaspike.jsf.api.message.JsfMessage;

import br.com.churchmanager.exception.DadosException;
import br.com.churchmanager.exception.NegocioException;
import br.com.churchmanager.exception.ViolacaoDeRestricaoException;
import br.com.churchmanager.jsf.FacesUtil;
import br.com.churchmanager.jsf.Msgs;
import br.com.churchmanager.jsf.primefaces.LazyDataModel;
import br.com.churchmanager.model.Dizimo;
import br.com.churchmanager.model.Pessoa;
import br.com.churchmanager.model.filter.DizimoFilter;
import br.com.churchmanager.report.GenericReport;
import br.com.churchmanager.service.DizimoService;
import br.com.churchmanager.service.PessoaService;
import br.com.churchmanager.util.BuscarArquivo;
import br.com.churchmanager.util.DataUtil;

@Named
@ViewScoped
public class DizimoMB implements Serializable {
	private static final long serialVersionUID = 1L;
	private Dizimo dizimo;
	private List<Dizimo> dizimos;
	private LazyDataModel<Dizimo> dizimosLazy;
	private DizimoFilter dizimoFilter;
	@Inject
	private DizimoService bo;
	@Inject
	private PessoaService pessoaService;

	@Inject
	private FacesUtil facesUtil;
	@Inject
	private JsfMessage<Msgs> msgs;

	@NotNull
	@Size(min = 17, max = 17, message = "Deve conter 17 caracteres")
	@Pattern(regexp = "[0-9]{17}", message = "Deve ser informado apenas números!")
	private String idPessoa;
	private List<Integer> listAnos = DataUtil.getAnos();

	@PostConstruct
	public void init() {
		Dizimo dizimo = null;
		this.dizimo = dizimo;
		this.getDizimoFilter().setMes(DataUtil.mes());
		this.getDizimoFilter().setAno(DataUtil.ano());
	}

	public String salvar() {
		try {
			this.bo.save(this.dizimo);
			msgs.addInfo().cadastradoComSucesso();
			this.dizimo = null;
			this.idPessoa = null;
		} catch (NegocioException | ViolacaoDeRestricaoException | DadosException e) {
			msgs.addWarn().atencao("Atenção!", e.getMessage());
		} finally {
			facesUtil.atualizarComponente("msg");
		}
		return null;
	}

	public String atualizar() {
		try {
			this.bo.save(this.dizimo);
			msgs.addInfo().editadoComSucesso();
			this.dizimo = null;
			this.idPessoa = null;
		} catch (NegocioException | ViolacaoDeRestricaoException | DadosException e) {
			msgs.addWarn().atencao("Atenção!", e.getMessage());
			return null;
		} finally {
			facesUtil.atualizarComponente("msg");
			facesUtil.manterMensagem();
		}
		return "/list/dizimo?faces-redirect=true";
	}

	public String filtrar() {
		this.dizimosLazy = this.bo.lazyList(this.dizimoFilter);
		this.listAnos = DataUtil.getAnos(this.dizimoFilter.getAno());
		return null;
	}

	public List<Integer> anos() {
		return this.listAnos;
	}

	public String deletar() {
		this.bo.delete(this.dizimo);
		this.dizimo = null;
		return null;
	}

	public List<Dizimo> perfis() {
		return this.bo.findAll();
	}

	public void buscarPessoa() {
		Pessoa pessoa = this.pessoaService.findByMatricula(this.getIdPessoa());
		if (pessoa != null) {
			this.getDizimo().setPessoa(pessoa);
			facesUtil.atualizarComponente("grid-pessoa-selecionada");
		} else {
			msgs.addWarn().atencao("Atenção!", "Nenhum dizimista foi encontrado para o código " + getIdPessoa() + "!");
			facesUtil.atualizarComponente("msg");
		}
	}

	public void cancelarSelecao() {
		this.getDizimo().setPessoa((Pessoa) null);
	}

	public Dizimo getDizimo() {
		if (this.dizimo == null) {
			this.dizimo = new Dizimo();
		}

		return this.dizimo;
	}

	public void setDizimo(Dizimo dizimo) {
		this.dizimo = dizimo;
	}

	public List<Dizimo> getDizimos() {
		if (this.dizimos == null) {
			this.dizimos = new ArrayList<>();
		}

		return this.dizimos;
	}

	public void setDizimos(List<Dizimo> dizimos) {
		this.dizimos = dizimos;
	}

	public LazyDataModel<Dizimo> getDizimosLazy() {
		if (this.dizimosLazy == null) {
			this.dizimosLazy = this.bo.lazyList(this.dizimoFilter);
		}

		return this.dizimosLazy;
	}

	public void setDizimosLazy(LazyDataModel<Dizimo> dizimosLazy) {
		this.dizimosLazy = dizimosLazy;
	}

	public DizimoFilter getDizimoFilter() {
		if (this.dizimoFilter == null) {
			this.dizimoFilter = new DizimoFilter();
		}

		return this.dizimoFilter;
	}

	public void setDizimoFilter(DizimoFilter dizimoFilter) {
		this.dizimoFilter = dizimoFilter;
	}

	public String getIdPessoa() {
		return this.idPessoa;
	}

	public void setIdPessoa(String idPessoa) {
		this.idPessoa = idPessoa;
	}

	public boolean desabilitarSelecaoPessoa(Pessoa pessoa) {
		return this.getDizimo().getPessoa().equals(pessoa);
	}

	public void gerarEnvelopeDizimo() {
		Map<String, Object> mapa = new HashMap<>();
		Pessoa pessoa = this.getDizimo().getPessoa();
		mapa.put("PESSOA_ID", pessoa.getId());
		mapa.put("ANO", Integer.valueOf(2018));
		mapa.put("LOGO", BuscarArquivo.porCaminho("/resources/img/Logoic.jpg"));
		mapa.put("IGREJA", "Igreja de Cristo - MN");
		mapa.put("ENVELOPE", BuscarArquivo.porCaminho("/resources/img/envelope.jpeg"));
		GenericReport.gerarPdfComConnection(mapa, "dizimo", pessoa.getNome(), true);
	}

	public List<Integer> getListAnos() {
		return listAnos;
	}

	public void setListAnos(List<Integer> listAnos) {
		this.listAnos = listAnos;
	}

}