package br.com.churchmanager.controllers;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.churchmanager.bo.MovimentacaoBO;
import br.com.churchmanager.bo.ParcelaMovimentacaoBO;
import br.com.churchmanager.exception.DadosException;
import br.com.churchmanager.exception.NegocioException;
import br.com.churchmanager.exception.ViolacaoDeRestricaoException;
import br.com.churchmanager.model.Movimentacao;
import br.com.churchmanager.model.ParcelaMovimentacao;
import br.com.churchmanager.model.Status;
import br.com.churchmanager.model.StatusMovimentacao;
import br.com.churchmanager.model.TipoMovimentacao;
import br.com.churchmanager.model.filter.MovimentacaoFilter;
import br.com.churchmanager.util.BuscaObjeto;
import br.com.churchmanager.util.DataUtil;
import br.com.churchmanager.util.Meses;
import br.com.churchmanager.util.MyLazyDataModel;
import br.com.churchmanager.util.faces.FacesUtil;

@Named
@ViewScoped
public class MovimentacaoMB implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Movimentacao movimentacao;
	private List<Movimentacao> movimentacaos;
	private MyLazyDataModel<Movimentacao> movimentacaosLazy;
	private MovimentacaoFilter movimentacaoFilter;
	private ParcelaMovimentacao parcela;
	@Inject
	private MovimentacaoBO bo;
	@Inject
	private ParcelaMovimentacaoBO parcelasBO;

	private List<Integer> listAnos = DataUtil.getAnos();

	@PostConstruct
	public void init() {
		Movimentacao movimentacao = BuscaObjeto.comParametroGET("id", this.bo);
		this.movimentacao = movimentacao;
		this.getMovimentacaoFilter().setMes(DataUtil.mes());
		this.getMovimentacaoFilter().setAno(DataUtil.ano());
		this.buscarParcelas();
	}

	public void buscarParcelas() {
		if (this.movimentacao != null) {
			List<ParcelaMovimentacao> parcelas = this.parcelasBO.busarParcelas(this.movimentacao);
			this.movimentacao.setParcelas(parcelas);
		}

	}

	public String salvar() {
		this.movimentacao.gerarParcelas();
		try {
			this.bo.salvar(this.movimentacao);
			FacesUtil.informacao("msg", "Cadastrado com sucesso!", this.movimentacao.toString());
			FacesUtil.atualizaComponente("msg");
			this.movimentacao = null;
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
			this.movimentacao.gerarParcelas();
			this.bo.atualizar(this.movimentacao);
			FacesUtil.informacao("msg", "Editado com sucesso!", this.movimentacao.toString());
			this.movimentacao = null;
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
		return "/list/movimentacao?faces-redirect=true";
	}

	public String filtrar() {
		this.movimentacaosLazy = this.bo.filtrar(this.movimentacaoFilter);
		this.listAnos = DataUtil.getAnos(this.movimentacaoFilter.getAno());
		return null;
	}

	public String deletar() {
		this.bo.deletar(this.movimentacao);
		this.movimentacao = null;
		return null;
	}

	public Status[] listarStatus() {
		return Status.values();
	}

	public List<Movimentacao> movimentacaos() {
		return this.bo.listar();
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

	public MyLazyDataModel<Movimentacao> getMovimentacaosLazy() {
		if (this.movimentacaosLazy == null) {
			this.movimentacaosLazy = this.bo.filtrar(this.movimentacaoFilter);
		}

		return this.movimentacaosLazy;
	}

	public void setMovimentacaosLazy(MyLazyDataModel<Movimentacao> movimentacaosLazy) {
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
		return this.listAnos ;
	}

	public boolean isStatusEmAbeto() {
		return this.getMovimentacao().getStatusMovimentacao().equals(StatusMovimentacao.EM_ABERTO);
	}

	public boolean isStatusEmAbeto(Movimentacao movimentacao) {
		return movimentacao.getStatusMovimentacao().equals(StatusMovimentacao.EM_ABERTO);
	}

	public boolean isStatusEmAbeto(ParcelaMovimentacao parcelaMovimentacao) {
		return parcelaMovimentacao.getStatusMovimentacao().equals(StatusMovimentacao.EM_ABERTO);
	}

	public boolean isStatusPago(ParcelaMovimentacao parcelaMovimentacao) {
		return parcelaMovimentacao.getStatusMovimentacao().equals(StatusMovimentacao.PAGO);
	}

	public boolean isEntrada() {
		return this.getMovimentacao().getTipoMovimentacao().equals(TipoMovimentacao.ENTRADA);
	}

	public boolean isSaida() {
		return this.getMovimentacao().getTipoMovimentacao().equals(TipoMovimentacao.SAIDA);
	}

	public boolean isReceber(Movimentacao movimentacao) {
		return movimentacao.getTipoMovimentacao().equals(TipoMovimentacao.ENTRADA)
				&& movimentacao.getStatusMovimentacao().equals(StatusMovimentacao.EM_ABERTO);
	}

	public boolean isPagar(Movimentacao movimentacao) {
		return movimentacao.getTipoMovimentacao().equals(TipoMovimentacao.SAIDA)
				&& movimentacao.getStatusMovimentacao().equals(StatusMovimentacao.EM_ABERTO);
	}

	public boolean isEntradaOuSaidaComStatusPago(Movimentacao movimentacao) {
		return !this.isReceber(movimentacao) && !this.isPagar(movimentacao);
	}

	public String tituloReceberPagar() {
		return this.isEntrada() ? "Receber" : "Pagar";
	}
	
	public boolean naoEstaEmAberto(ParcelaMovimentacao parcela) {
		return !isStatusEmAbeto(parcela);
	}

	public String alterarStatusDaParcela() {
		if (this.getParcela().getDataPagamento() != null) {
			this.getParcela().setStatusMovimentacao(StatusMovimentacao.PAGO);
			Movimentacao movimentacao = this.parcela.getMovimentacao();
			List<ParcelaMovimentacao> listaDeParcelas = this.parcelasBO.busarParcelas(movimentacao);
			movimentacao.setParcelas(listaDeParcelas);
			long parcelasEmAberto = movimentacao.getParcelas().stream().filter((p) -> {
				return this.isStatusEmAbeto(p);
			}).count();
			if (parcelasEmAberto - 1L == 0L) {
				movimentacao.setStatusMovimentacao(StatusMovimentacao.PAGO);
			}

			try {
				this.movimentacao = this.bo.atualizar(movimentacao);
				this.parcela = this.parcelasBO.atualizar(this.parcela);
				this.movimentacao.atualizaParcela(this.parcela);
				FacesUtil.informacao("msg-pagar-receber", "Editado com sucesso!", this.parcela.toString());
			} catch (Exception e) {
				FacesUtil.atencao("msg-pagar-receber", "Atenção!", e.getMessage());
				e.printStackTrace();
			} finally {
				FacesUtil.atualizaComponente("msg-pagar-receber");
			}
		} else {
			FacesUtil.atencao("msg-pagar-receber", "É necessário informar a data!", "");
			FacesUtil.atualizaComponente("msg-pagar-receber");
		}

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
		this.getParcela().setDataPagamento((Date) null);
		Movimentacao movimentacao = this.parcela.getMovimentacao();
		List<ParcelaMovimentacao> listaDeParcelas = this.parcelasBO.busarParcelas(movimentacao);
		movimentacao.setParcelas(listaDeParcelas);
		long parcelasPagas = movimentacao.getParcelas().stream().filter((p) -> {
			return this.isStatusEmAbeto(p);
		}).count();
		if (parcelasPagas == 0L) {
			movimentacao.setStatusMovimentacao(StatusMovimentacao.EM_ABERTO);
		}
		try {
			this.movimentacao = this.bo.atualizar(movimentacao);
			this.parcela = this.parcelasBO.atualizar(this.parcela);
			this.movimentacao.atualizaParcela(this.parcela);
			FacesUtil.informacao("msg-pagar-receber", "Editado com sucesso!", this.parcela.toString());
		} catch (Exception e) {
			FacesUtil.atencao("msg-pagar-receber", "Atenção!", e.getMessage());
			e.printStackTrace();
		} finally {
			FacesUtil.atualizaComponente("msg-pagar-receber");
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