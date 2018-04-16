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
import javax.validation.constraints.Pattern;

import br.com.churchmanager.bo.DizimoBO;
import br.com.churchmanager.bo.PessoaBO;
import br.com.churchmanager.exception.DadosException;
import br.com.churchmanager.exception.NegocioException;
import br.com.churchmanager.exception.ViolacaoDeRestricaoException;
import br.com.churchmanager.model.Dizimo;
import br.com.churchmanager.model.Pessoa;
import br.com.churchmanager.model.Status;
import br.com.churchmanager.model.filter.DizimoFilter;
import br.com.churchmanager.report.GenericReport;
import br.com.churchmanager.util.BuscaObjeto;
import br.com.churchmanager.util.BuscarArquivo;
import br.com.churchmanager.util.DataUtil;
import br.com.churchmanager.util.MyLazyDataModel;
import br.com.churchmanager.util.faces.FacesUtil;

@Named
@ViewScoped
public class DizimoMB implements Serializable {
	private static final long serialVersionUID = 1L;
	private Dizimo dizimo;
	private List<Dizimo> dizimos;
	private MyLazyDataModel<Dizimo> dizimosLazy;
	private DizimoFilter dizimoFilter;
	private String mes;
	private String ano;
	@Inject
	private DizimoBO bo;
	@Inject
	PessoaBO pessoaBO;
	@Pattern(regexp = "[a-f0-9]{32}")
	private String idPessoa;

	@PostConstruct
	public void init() {
		Dizimo dizimo = (Dizimo) BuscaObjeto.comParametroGET(Dizimo.class, "id", this.bo);
		this.dizimo = dizimo;
		this.getDizimoFilter().setMes(DataUtil.mes());
		this.getDizimoFilter().setAno(DataUtil.ano());
	}

	public String salvar() {
		try {
			this.bo.salvar(this.dizimo);
			FacesUtil.informacao("msg", "Cadastro com sucesso!", this.dizimo.toString());
			FacesUtil.atualizaComponente("msg");
			this.dizimo = null;
			this.idPessoa = null;
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
			this.bo.atualizar(this.dizimo);
			FacesUtil.informacao("msg", "Editado com sucesso!", this.dizimo.toString());
			this.dizimo = null;
			this.idPessoa = null;
		} catch (NegocioException e) {
			FacesUtil.atencao("msg", "Atenção!", e.getMessage());
			e.printStackTrace();
			return null;
		} catch (ViolacaoDeRestricaoException e) {
			FacesUtil.atencao("msg", "Atenção!", e.getMessage());
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
		return "/list/dizimo?faces-redirect=true";
	}

	public String filtrar() {
		this.dizimosLazy = this.bo.filtrar(this.dizimoFilter);
		return null;
	}

	public String deletar() {
		this.bo.deletar(this.dizimo);
		this.dizimo = null;
		return null;
	}

	public List<Dizimo> perfis() {
		return this.bo.listar();
	}

	public Status[] listarStatus() {
		return Status.values();
	}

	public void buscarPessoa() {
		Pessoa pessoa = this.pessoaBO.buscarPorId(this.getIdPessoa());
		this.getDizimo().setPessoa(pessoa);
		FacesUtil.atualizaComponente("grid-pessoa-selecionada");
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

	public MyLazyDataModel<Dizimo> getDizimosLazy() {
		if (this.dizimosLazy == null) {
			this.dizimosLazy = this.bo.filtrar(this.dizimoFilter);
		}

		return this.dizimosLazy;
	}

	public void setDizimosLazy(MyLazyDataModel<Dizimo> dizimosLazy) {
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

	public String getMes() {
		return this.mes;
	}

	public void setMes(String mes) {
		this.mes = mes;
	}

	public String getAno() {
		return this.ano;
	}

	public void setAno(String ano) {
		this.ano = ano;
	}
}