package br.com.churchmanager.service;

import java.util.List;

import br.com.churchmanager.model.Movimentacao;
import br.com.churchmanager.model.ParcelaMovimentacao;
import br.com.churchmanager.model.dto.DetalheMovimentacao;
import br.com.churchmanager.model.dto.MovimentacaoAnual;
import br.com.churchmanager.model.dto.MovimentacaoCategoria;
import br.com.churchmanager.model.dto.Totais;
import br.com.churchmanager.model.filter.ParcelaMovimentacaoFilter;

public interface ParcelaMovimentacaoService extends Service<ParcelaMovimentacao> {

	List<DetalheMovimentacao> ultimosLancamentos(ParcelaMovimentacaoFilter parcelaFilter);

	Totais movimentacoes(ParcelaMovimentacaoFilter filter);

	List<MovimentacaoCategoria> custosPorCategoria(ParcelaMovimentacaoFilter filter);

	List<MovimentacaoAnual> movimentacaoUltimos12Meses(ParcelaMovimentacaoFilter filter);

	List<ParcelaMovimentacao> busarParcelas(Movimentacao movimentacao);

}
