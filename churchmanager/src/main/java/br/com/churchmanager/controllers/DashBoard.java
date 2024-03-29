package br.com.churchmanager.controllers;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;

import org.apache.deltaspike.jsf.api.message.JsfMessage;
import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.CategoryAxis;
import org.primefaces.model.chart.ChartSeries;
import org.primefaces.model.chart.LineChartModel;
import org.primefaces.model.chart.PieChartModel;

import br.com.churchmanager.jsf.Msgs;
import br.com.churchmanager.model.Evento;
import br.com.churchmanager.model.FormaMovimentacao;
import br.com.churchmanager.model.StatusMovimentacao;
import br.com.churchmanager.model.TipoMovimentacao;
import br.com.churchmanager.model.Usuario;
import br.com.churchmanager.model.dto.Aniversariante;
import br.com.churchmanager.model.dto.DetalheMovimentacao;
import br.com.churchmanager.model.dto.Dizimista;
import br.com.churchmanager.model.dto.MembrosPorFaixaEtaria;
import br.com.churchmanager.model.dto.MovimentacaoAnual;
import br.com.churchmanager.model.dto.MovimentacaoCategoria;
import br.com.churchmanager.model.dto.PercentualDizimista;
import br.com.churchmanager.model.dto.PessoaAtividaEclesiastica;
import br.com.churchmanager.model.dto.Totais;
import br.com.churchmanager.model.filter.DizimoFilter;
import br.com.churchmanager.model.filter.EventoFilter;
import br.com.churchmanager.model.filter.ParcelaMovimentacaoFilter;
import br.com.churchmanager.model.filter.PessoaFilter;
import br.com.churchmanager.seguranca.UsuarioLogado;
import br.com.churchmanager.service.DizimoService;
import br.com.churchmanager.service.EventoService;
import br.com.churchmanager.service.ParcelaMovimentacaoService;
import br.com.churchmanager.service.PessoaService;
import br.com.churchmanager.util.DataUtil;
import br.com.churchmanager.util.Meses;

@Named
@ViewScoped
public class DashBoard implements Serializable {

	private static final long serialVersionUID = -8423931165560787194L;

	private BarChartModel membresia;
	private BarChartModel pessoasPorAtividadesEclesiasticas;
	private LineChartModel movimentacaoAnual;
	private PieChartModel custosPorCategoria;
	private List<DetalheMovimentacao> ultimosLancamentos;
	private BigDecimal[] totalizadores;
	private Totais totais;
	private String mes;
	private String ano;
	private List<Integer> listAnos = DataUtil.getAnos();
	
	@Inject
	private HttpServletRequest request;
	
	@UsuarioLogado
	private Usuario usuarioLogado;
	
	@Inject
	private ParcelaMovimentacaoService parcelasService;
	
	@Inject
	private PessoaService pessoaService;
	
	@Inject
	private EventoService eventoService;
	
	private PercentualDizimista percentualDizimista;
	
	@Inject
	private DizimoService dizimoService;
	
	@Inject
	private JsfMessage<Msgs> message;

	public void loginSucesso() {
		if ("true".equals(this.request.getParameter("logado"))) {
			message.addInfo().bemVindo(this.usuarioLogado.getNomeCompleto());
		}

	}

	@PostConstruct
	public void init() {
		this.mes = DataUtil.mes();
		this.ano = DataUtil.ano();
		updateDados();
	}

	public void updateDados() {
		this.criarGraficos();
		this.updateTotais();
		this.updateContadores();
		this.updateUltimosLancamentos();
		this.updatePercentualDizimista();

	}

	public void updateAno() {
		this.listAnos = DataUtil.getAnos(ano);
	}

	private void criarGraficos() {
		this.criarGraficoMembresia();
		this.criarGraficoMovimentacaoAnual();
		this.criarGraficoCustoPorCategoria();
		this.criarGraficoPessoasPorAtividadesEclesiasticas();
	}

	private void criarGraficoMembresia() {
		this.membresia = new BarChartModel();
		ChartSeries boys = new ChartSeries("Homens");
		ChartSeries girls = new ChartSeries("Mulheres");
		List<MembrosPorFaixaEtaria> membresiaFaixaEtaria = this.membresiaFaixaEtaria();
		Iterator<MembrosPorFaixaEtaria> arg4 = membresiaFaixaEtaria.iterator();

		while (arg4.hasNext()) {
			MembrosPorFaixaEtaria m = arg4.next();
			boys.set(m.getFaixaEtaria(), m.getM());
			girls.set(m.getFaixaEtaria(), m.getF());
		}

		this.membresia.addSeries(boys);
		this.membresia.addSeries(girls);
		this.membresia.setSeriesColors("2196f3, e91e63");
		this.membresia.setAnimate(true);
		this.membresia.setLegendPosition("ne");
	}

	private void criarGraficoPessoasPorAtividadesEclesiasticas() {
		this.pessoasPorAtividadesEclesiasticas = new BarChartModel();
		List<PessoaAtividaEclesiastica> pessoasPorAtividadeEclesiastica = this.pessoasPorAtividadeEclesiastica();
		Iterator<PessoaAtividaEclesiastica> it = pessoasPorAtividadeEclesiastica.iterator();

		while (it.hasNext()) {
			PessoaAtividaEclesiastica pessoaAtividade = it.next();
			ChartSeries chartSeries = new ChartSeries(pessoaAtividade.getCategoria());
			chartSeries.set(pessoaAtividade.getCategoria(), pessoaAtividade.getQuantidade());
			this.pessoasPorAtividadesEclesiasticas.addSeries(chartSeries);
		}

		this.pessoasPorAtividadesEclesiasticas.setSeriesColors(
				"D60000, FF530E, FFC801, 93C700, 0E99DA, C10250, 324C5D, 46B19D, 34A73F, 3E2E86, CA1E2C, B6D124, F9AB15, 2196f3, e91e63");
		this.pessoasPorAtividadesEclesiasticas.setAnimate(true);
		this.pessoasPorAtividadesEclesiasticas.setLegendPosition("s");
		this.pessoasPorAtividadesEclesiasticas.setLegendCols(5);
		this.pessoasPorAtividadesEclesiasticas.getAxes().put(AxisType.X, new CategoryAxis("Atividades"));
		Axis yAxis21 = this.pessoasPorAtividadesEclesiasticas.getAxis(AxisType.Y);
		yAxis21.setLabel("Quantidade de pessoas");
	}

	private void criarGraficoMovimentacaoAnual() {
		this.movimentacaoAnual = new LineChartModel();
		List<MovimentacaoAnual> movimentacaoUltimos12Meses = this.movimentacaoUltimos12Meses();
		Collections.reverse(movimentacaoUltimos12Meses);
		ChartSeries entradasPagas = new ChartSeries("Entradas");
		ChartSeries entradasEmAberto = new ChartSeries("Receber");
		ChartSeries saidasPagas = new ChartSeries("Saídas");
		ChartSeries saidasEmAberto = new ChartSeries("Pagar");

		for (int yAxis2 = 0; yAxis2 < movimentacaoUltimos12Meses.size(); ++yAxis2) {
			MovimentacaoAnual mov = movimentacaoUltimos12Meses.get(yAxis2);
			if (mov.getTipo().equals(TipoMovimentacao.ENTRADA.toString())
					&& mov.getStatus().equals(StatusMovimentacao.PAGO.toString())) {
				entradasPagas.set(mov.getData(), Double.valueOf(mov.getValor()));
			} else if (mov.getTipo().equals(TipoMovimentacao.ENTRADA.toString())
					&& mov.getStatus().equals(StatusMovimentacao.EM_ABERTO.toString())) {
				entradasEmAberto.set(mov.getData(), Double.valueOf(mov.getValor()));
			} else if (mov.getTipo().equals(TipoMovimentacao.SAIDA.toString())
					&& mov.getStatus().equals(StatusMovimentacao.PAGO.toString())) {
				saidasPagas.set(mov.getData(), Double.valueOf(mov.getValor()));
			} else {
				saidasEmAberto.set(mov.getData(), Double.valueOf(mov.getValor()));
			}
		}

		this.movimentacaoAnual.addSeries(entradasPagas);
		this.movimentacaoAnual.addSeries(entradasEmAberto);
		this.movimentacaoAnual.addSeries(saidasPagas);
		this.movimentacaoAnual.addSeries(saidasEmAberto);
		this.movimentacaoAnual.setSeriesColors("4CAF50, 03A9F4, E53935, ffe500");
		this.movimentacaoAnual.setAnimate(true);
		this.movimentacaoAnual.setLegendPosition("w");
		this.movimentacaoAnual.setShowPointLabels(false);
		this.movimentacaoAnual.getAxes().put(AxisType.X, new CategoryAxis("Meses"));
		Axis arg7 = this.movimentacaoAnual.getAxis(AxisType.Y);
		arg7.setLabel("Valor");
	}

	private void criarGraficoCustoPorCategoria() {
		this.custosPorCategoria = new PieChartModel();
		List<MovimentacaoCategoria> custosPorCategoria2 = this.custosPorCategoria();
		Iterator<MovimentacaoCategoria> arg2 = custosPorCategoria2.iterator();

		while (arg2.hasNext()) {
			MovimentacaoCategoria movimentacaoCategoria = arg2.next();
			this.custosPorCategoria.set(movimentacaoCategoria.getNome(),
					Double.valueOf(movimentacaoCategoria.getValorParcela()));
		}

		this.custosPorCategoria.setResetAxesOnResize(true);
		this.custosPorCategoria.setShowDataLabels(true);
		this.custosPorCategoria.setLegendCols(custosPorCategoria2.size() > 5 ? 5 : custosPorCategoria2.size());
		this.custosPorCategoria.setLegendPosition("s");
		this.custosPorCategoria.setSeriesColors(
				"D60000, FF530E, FFC801, 93C700, 0E99DA, C10250, 324C5D, 46B19D, 34A73F, 3E2E86, CA1E2C, B6D124, F9AB15");
	}

	private void updateUltimosLancamentos() {
		ParcelaMovimentacaoFilter parcelaFilter = new ParcelaMovimentacaoFilter();
		parcelaFilter.setMes(this.mes);
		parcelaFilter.setAno(this.ano);
		this.ultimosLancamentos = this.parcelasService.ultimosLancamentos(parcelaFilter);
	}

	public List<DetalheMovimentacao> ultimosLancamentosEmConta() {
		return this.ultimosLancamentos.stream()
				.filter(m -> m.getForma() != null && m.getForma().equals(FormaMovimentacao.EM_CONTA.toString()))
				.collect(Collectors.toList());
	}

	public List<DetalheMovimentacao> ultimosLancamentosEntradas() {
		return this.ultimosLancamentos.stream()
				.filter(m -> m.getStatus().equals(StatusMovimentacao.PAGO.toString())
						&& m.getTipo().equals(TipoMovimentacao.ENTRADA.toString()) && m.getForma() != null
						&& m.getForma().equals(FormaMovimentacao.EM_ESPECIE.toString()))
				.collect(Collectors.toList());
	}

	public List<DetalheMovimentacao> ultimosLancamentosSaidas() {
		return this.ultimosLancamentos.stream()
				.filter(m -> m.getStatus().equals(StatusMovimentacao.PAGO.toString())
						&& m.getTipo().equals(TipoMovimentacao.SAIDA.toString()) && m.getForma() != null
						&& m.getForma().equals(FormaMovimentacao.EM_ESPECIE.toString()))
				.collect(Collectors.toList());
	}

	public List<DetalheMovimentacao> proximasEntradas() {
		return this.ultimosLancamentos.stream()
				.filter(m -> m.getStatus().equals(StatusMovimentacao.EM_ABERTO.toString())
						&& m.getTipo().equals(TipoMovimentacao.ENTRADA.toString()))
				.collect(Collectors.toList());
	}

	public List<DetalheMovimentacao> proximasSaidas() {
		return this.ultimosLancamentos.stream()
				.filter(m -> m.getStatus().equals(StatusMovimentacao.EM_ABERTO.toString())
						&& m.getTipo().equals(TipoMovimentacao.SAIDA.toString()))
				.collect(Collectors.toList());
	}

	public void updatePercentualDizimista() {
		DizimoFilter filter = new DizimoFilter();
		filter.setMes(this.mes);
		filter.setAno(this.ano);
		this.percentualDizimista = this.dizimoService.percentualDizimista(filter);
	}

	private void updateTotais() {
		ParcelaMovimentacaoFilter filter = new ParcelaMovimentacaoFilter();
		filter.setMes(this.mes);
		filter.setAno(this.ano);
		this.totais = this.parcelasService.movimentacoes(filter);
	}

	public double totalEntradasPagas() {
		return this.getTotais().getRecebidas();
	}

	public double totalSaidasPagas() {
		return this.getTotais().getPagas();
	}

	public double saldoFaturado() {
		return totalEntradasPagas() - totalSaidasPagas();
	}

	public double saldoAFaturar() {
		return totalEntradasEmAberto() - totalSaidasEmAberto();
	}

	public double totalEntradasEmAberto() {
		return this.getTotais().getaReceber();
	}

	public double totalSaidasEmAberto() {
		return this.getTotais().getaPagar();
	}

	public BigDecimal quantidadeDeMembros() {
		return valueBigDecimal(totalizadores[0]);
	}

	public BigDecimal quantidadeDeHomem() {
		return valueBigDecimal(totalizadores[1]);
	}

	public BigDecimal quantidadeDeMulher() {
		return valueBigDecimal(totalizadores[2]);
	}

	public BigDecimal valueBigDecimal(BigDecimal value) {
		return value != null ? value : BigDecimal.ZERO;
	}

	private void updateContadores() {
		PessoaFilter filter = new PessoaFilter();
		filter.setAno(getAno());
		filter.setMes(getMes());
		this.totalizadores = this.pessoaService.quantidadeDeMembros(filter);
	}

	public List<MovimentacaoCategoria> custosPorCategoria() {
		ParcelaMovimentacaoFilter filter = new ParcelaMovimentacaoFilter();
		filter.setMes(this.mes);
		filter.setAno(this.ano);
		return this.parcelasService.custosPorCategoria(filter);
	}

	public List<MovimentacaoAnual> movimentacaoUltimos12Meses() {
		ParcelaMovimentacaoFilter filter = new ParcelaMovimentacaoFilter();
		filter.setMes(this.mes);
		filter.setAno(this.ano);
		return this.parcelasService.movimentacaoUltimos12Meses(filter);
	}

	public List<Aniversariante> aniversariantesDoMes() {
		PessoaFilter filter = new PessoaFilter();
		filter.setMes(this.mes);
		filter.setAno(this.ano);
		return this.pessoaService.aniversariantesDoMes(filter);
	}

	public List<Dizimista> listarDizimistas() {
		PessoaFilter filter = new PessoaFilter();
		filter.setMes(this.mes);
		filter.setAno(this.ano);
		return this.pessoaService.listarDizimistas(filter);
	}

	public List<MembrosPorFaixaEtaria> membresiaFaixaEtaria() {
		PessoaFilter filter = new PessoaFilter();
		filter.setMes(this.mes);
		filter.setAno(this.ano);
		return this.pessoaService.membresiaFaixaEtaria(filter);
	}

	public List<PessoaAtividaEclesiastica> pessoasPorAtividadeEclesiastica() {
		PessoaFilter filter = new PessoaFilter();
		filter.setMes(this.mes);
		filter.setAno(this.ano);
		return this.pessoaService.pessoasPorAtividadeEclesiastica(filter);
	}

	public Meses mesAtual() {
		return Meses.mesPorNumero(this.mes);
	}

	public List<Evento> eventosDoMes() {
		EventoFilter filter = new EventoFilter();
		filter.setMes(this.mes);
		return this.eventoService.eventosDoMes(filter);
	}

	public LineChartModel getMovimentacaoAnual() {
		return this.movimentacaoAnual;
	}

	public BarChartModel getMembresia() {
		return this.membresia;
	}

	public PieChartModel getCustoPorCategoria() {
		return this.custosPorCategoria;
	}

	public BarChartModel getPessoasPorAtividadesEclesiasticas() {
		return this.pessoasPorAtividadesEclesiasticas;
	}

	public PercentualDizimista getPercentualDizimista() {
		if (this.percentualDizimista == null) {
			this.percentualDizimista = new PercentualDizimista(0, 0, new BigDecimal(0));
		}

		return this.percentualDizimista;
	}

	public void setPercentualDizimista(PercentualDizimista percentualDizimista) {
		this.percentualDizimista = percentualDizimista;
	}

	public Totais getTotais() {
		if (totais == null) {
			totais = new Totais(Integer.parseInt(mes), Integer.parseInt(ano), 0d, 0d, 0d, 0d);
		}
		return totais;
	}

	public String getMes() {
		return mes;
	}

	public void setMes(String mes) {
		this.mes = mes;
	}

	public String getAno() {
		return ano;
	}

	public void setAno(String ano) {
		this.ano = ano;
	}

	public List<Integer> getListAnos() {
		return listAnos;
	}

	public void setListAnos(List<Integer> listAnos) {
		this.listAnos = listAnos;
	}
}