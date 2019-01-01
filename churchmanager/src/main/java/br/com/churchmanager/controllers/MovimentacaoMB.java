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
import br.com.churchmanager.model.Movimentacao;
import br.com.churchmanager.model.ParcelaMovimentacao;
import br.com.churchmanager.model.StatusMovimentacao;
import br.com.churchmanager.model.filter.MovimentacaoFilter;
import br.com.churchmanager.service.MovimentacaoService;
import br.com.churchmanager.service.ParcelaMovimentacaoService;
import br.com.churchmanager.util.DataUtil;
import br.com.churchmanager.util.Meses;

@Named
@ViewScoped
public class MovimentacaoMB implements Serializable {

	private static final long serialVersionUID = 1L;

	private Movimentacao movimentacao;
	private List<Movimentacao> movimentacaos;
	private LazyDataModel<Movimentacao> movimentacaosLazy;
	private MovimentacaoFilter movimentacaoFilter;
	private ParcelaMovimentacao parcela;
	@Inject
	private MovimentacaoService movimentacaoService;
	@Inject
	private ParcelaMovimentacaoService parcelasService;

	private List<Integer> listAnos = DataUtil.getAnos();

	@Inject
	private FacesUtil facesUtil;
	@Inject
	private JsfMessage<Msgs> msgs;

	@PostConstruct
	public void init() {
		Movimentacao movimentacao = null;
		this.movimentacao = movimentacao;
		this.getMovimentacaoFilter().setMes(DataUtil.mes());
		this.getMovimentacaoFilter().setAno(DataUtil.ano());
		this.buscarParcelas();
	}

	public void buscarParcelas() {
		if (this.movimentacao != null) {
			List<ParcelaMovimentacao> parcelas = this.parcelasService.busarParcelas(this.movimentacao);
			this.movimentacao.setParcelas(parcelas);
		}

	}

	public String salvar() {
		this.movimentacao.gerarParcelas();
		try {
			this.movimentacaoService.save(this.movimentacao);
			msgs.addInfo().cadastradoComSucesso();
			this.movimentacao = null;
		} catch (NegocioException | ViolacaoDeRestricaoException | DadosException e) {
			msgs.addWarn().atencao("Atenção!", e.getMessage());
		} finally {
			facesUtil.atualizarComponente("msg");
		}
		return null;
	}

	public String atualizar() {
		try {
			this.movimentacao.gerarParcelas();
			this.movimentacaoService.save(this.movimentacao);
			msgs.addInfo().editadoComSucesso();
			this.movimentacao = null;
		} catch (NegocioException | ViolacaoDeRestricaoException | DadosException e) {
			msgs.addWarn().atencao("Atenção!", e.getMessage());
			return null;
		} finally {
			facesUtil.atualizarComponente("msg");
			facesUtil.manterMensagem();
		}
		return "/list/movimentacao?faces-redirect=true";
	}

	public String filtrar() {
		this.movimentacaosLazy = this.movimentacaoService.lazyList(this.movimentacaoFilter);
		this.listAnos = DataUtil.getAnos(this.movimentacaoFilter.getAno());
		return null;
	}

	public String deletar() {
		this.movimentacaoService.delete(this.movimentacao);
		this.movimentacao = null;
		return null;
	}

	public List<Movimentacao> movimentacaos() {
		return this.movimentacaoService.findAll();
	}

	public Movimentacao getMovimentacao() {
		if (this.movimentacao == null) {
			this.movimentacao = new Movimentacao();
		}

		return this.movimentacao;
	}

	public void setMovimentacao(Movimentacao movimentacao) {
		this.movimentacao = movimentacao;
	}

	public List<Movimentacao> getMovimentacaos() {
		if (this.movimentacaos == null) {
			this.movimentacaos = new ArrayList<>();
		}

		return this.movimentacaos;
	}

	public void setMovimentacaos(List<Movimentacao> movimentacaos) {
		this.movimentacaos = movimentacaos;
	}

	public LazyDataModel<Movimentacao> getMovimentacaosLazy() {
		if (this.movimentacaosLazy == null) {
			this.movimentacaosLazy = this.movimentacaoService.lazyList(this.movimentacaoFilter);
		}

		return this.movimentacaosLazy;
	}

	public void setMovimentacaosLazy(LazyDataModel<Movimentacao> movimentacaosLazy) {
		this.movimentacaosLazy = movimentacaosLazy;
	}

	public MovimentacaoFilter getMovimentacaoFilter() {
		if (this.movimentacaoFilter == null) {
			this.movimentacaoFilter = new MovimentacaoFilter();
		}

		return this.movimentacaoFilter;
	}

	public ParcelaMovimentacao getParcela() {
		if (parcela == null) {
			parcela = new ParcelaMovimentacao();
		}
		return this.parcela;
	}

	public void setParcela(ParcelaMovimentacao parcela) {
		this.parcela = parcela;
	}

	public void setMovimentacaoFilter(MovimentacaoFilter movimentacaoFilter) {
		this.movimentacaoFilter = movimentacaoFilter;
	}

	public List<Meses> meses() {
		return DataUtil.getListMeses();
	}

	public List<Integer> anos() {
		return this.listAnos;
	}

	public String tituloReceberPagar() {
		return this.getMovimentacao().isEntrada() ? "Receber" : "Pagar";
	}

	public String alterarStatusDaParcela() {
		if (this.getParcela().getDataPagamento() != null) {
			this.getParcela().setStatusMovimentacao(StatusMovimentacao.PAGO);
			Movimentacao movimentacao = this.parcela.getMovimentacao();
			List<ParcelaMovimentacao> listaDeParcelas = this.parcelasService.busarParcelas(movimentacao);
			movimentacao.setParcelas(listaDeParcelas);
			long parcelasEmAberto = movimentacao.getParcelas().stream().filter(ParcelaMovimentacao::isStatusEmAbeto)
					.count();
			if (parcelasEmAberto - 1L == 0L) {
				movimentacao.setStatusMovimentacao(StatusMovimentacao.PAGO);
			}

			try {
				this.movimentacao = this.movimentacaoService.update(movimentacao);
				this.parcela = this.parcelasService.update(this.parcela);
				this.movimentacao.atualizaParcela(this.parcela);
				msgs.addInfo().info("Editado com sucesso!", this.parcela.toString());
			} catch (Exception e) {
				msgs.addWarn().atencao("Atenção!", e.getMessage());
			}
		} else {
			msgs.addWarn().atencao("É necessário informar a data!", "");
		}
		facesUtil.atualizarComponente("msg-pagar-receber");

		return null;
	}

	public String tamanhoDaTelaDeDetalhes() {
		return this.getMovimentacao().isParcelado() ? "1000" : "600";
	}

	public boolean atualizarFormaDePagamento() {
		return this.getMovimentacao().getStatusMovimentacao().equals(StatusMovimentacao.PAGO);
	}

	public String cancelarAlterarStatusDaParcela() {
		this.getParcela().setStatusMovimentacao(StatusMovimentacao.EM_ABERTO);
		this.getParcela().setDataPagamento(null);
		Movimentacao movimentacao = this.parcela.getMovimentacao();
		List<ParcelaMovimentacao> listaDeParcelas = this.parcelasService.busarParcelas(movimentacao);
		movimentacao.setParcelas(listaDeParcelas);
		long parcelasPagas = movimentacao.getParcelas().stream().filter(ParcelaMovimentacao::isStatusEmAbeto).count();
		if (parcelasPagas == 0L) {
			movimentacao.setStatusMovimentacao(StatusMovimentacao.EM_ABERTO);
		}
		try {
			this.movimentacao = this.movimentacaoService.update(movimentacao);
			this.parcela = this.parcelasService.update(this.parcela);
			this.movimentacao.atualizaParcela(this.parcela);
			msgs.addInfo().editadoComSucesso();
		} catch (Exception e) {
			msgs.addWarn().atencao("Atenção!", e.getMessage());

		} finally {
			facesUtil.atualizarComponente("msg-pagar-receber");
		}
		return null;
	}

	public List<Integer> getListAnos() {
		return listAnos;
	}

	public void setListAnos(List<Integer> listAnos) {
		this.listAnos = listAnos;
	}
}