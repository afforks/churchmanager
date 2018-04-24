package br.com.churchmanager.dao;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

import br.com.churchmanager.dao.generic.DAO;
import br.com.churchmanager.model.Movimentacao;
import br.com.churchmanager.model.ParcelaMovimentacao;
import br.com.churchmanager.model.filter.ParcelaMovimentacaoFilter;
import br.com.churchmanager.model.group.DetalheMovimentacao;
import br.com.churchmanager.model.group.MovimentacaoAnual;
import br.com.churchmanager.model.group.MovimentacaoCategoria;
import br.com.churchmanager.model.group.Totais;
import br.com.churchmanager.util.DataUtil;

public class ParcelaMovimentacaoDAO extends DAO<ParcelaMovimentacao> implements Serializable {
	private static final long serialVersionUID = 1823847283499L;
	@Inject
	private EntityManager entityManager;

	public ParcelaMovimentacaoDAO() {
		super(ParcelaMovimentacao.class);
	}

	public List<ParcelaMovimentacao> busarParcelas(Movimentacao movimentacao) {
		ArrayList<Criterion> restrictions = new ArrayList<>();
		restrictions.add(Restrictions.eq("movimentacao", movimentacao));
		return this.listarPorAtributosERestricoes("dataVencimento", true, restrictions, null);
	}

	public List<DetalheMovimentacao> ultimosLancamentos(ParcelaMovimentacaoFilter filter) {
		int mes = Integer.valueOf(filter.getMes()).intValue();
		int ano = Integer.valueOf(filter.getAno()).intValue();
		String sql = "select * from movimentacoes where mes = :mes and ano = :ano";
		ArrayList<DetalheMovimentacao> resultList = new ArrayList<>();
		Session session = (Session) this.entityManager.unwrap(Session.class);
		SQLQuery query = session.createSQLQuery(sql);
		query.setParameter("mes", Integer.valueOf(mes));
		query.setParameter("ano", Integer.valueOf(ano));
		query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
		List<?> data = query.list();
		Iterator<?> arg9 = data.iterator();

		while (arg9.hasNext()) {
			Object o = arg9.next();
			Map<?, ?> map = (Map<?, ?>) o;
			DetalheMovimentacao d = new DetalheMovimentacao();
			d.setMes((BigInteger) map.get("mes"));
			d.setAno((BigInteger) map.get("ano"));
			d.setNome((String) map.get("nome"));
			d.setDescricao((String) map.get("descricao"));
			d.setDataVencimento((Date) map.get("data_vencimento"));
			d.setDataPagamento((Date) map.get("data_pagamento"));
			d.setValor((double) ((Float) map.get("valor")).floatValue());
			d.setTipo((String) map.get("tipo"));
			d.setForma((String) map.get("forma"));
			d.setStatus((String) map.get("status"));
			resultList.add(d);
		}

		return resultList;
	}

	public Totais movimentacoes(ParcelaMovimentacaoFilter filter) {
		String sql = "select * from totalizadores where mes = :mes and ano = :ano";
		Session session = (Session) this.entityManager.unwrap(Session.class);
		SQLQuery query = session.createSQLQuery(sql);
		int mes = Integer.valueOf(filter.getMes()).intValue();
		int ano = Integer.valueOf(filter.getAno()).intValue();
		query.setParameter("mes", Integer.valueOf(mes));
		query.setParameter("ano", Integer.valueOf(ano));
		query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
		Map<?, ?> dados = (Map<?, ?>) query.uniqueResult();
		Totais totais = null;

		if (dados != null) {
			totais = new Totais(((Integer) dados.get("mes")).intValue(), ((Integer) dados.get("ano")).intValue(),
					((Double) dados.get("recebidas")).doubleValue(), ((Double) dados.get("a_receber")).doubleValue(),
					((Double) dados.get("pagas")).doubleValue(), ((Double) dados.get("a_pagar")).doubleValue());
		}
		return totais;
	}

	public List<MovimentacaoCategoria> custosPorCategoria(ParcelaMovimentacaoFilter filter) {
		String sql = "select * from entradas_e_saidas_por_categoria where mes = :mes and ano = :ano and tipo = \'SAIDA\' ";
		ArrayList<MovimentacaoCategoria> resultList = new ArrayList<>();
		Session session = (Session) this.entityManager.unwrap(Session.class);
		SQLQuery query = session.createSQLQuery(sql);
		int mes = Integer.valueOf(filter.getMes()).intValue();
		int ano = Integer.valueOf(filter.getAno()).intValue();
		query.setParameter("mes", Integer.valueOf(mes));
		query.setParameter("ano", Integer.valueOf(ano));
		query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
		List<?> data = query.list();
		Iterator<?> arg9 = data.iterator();

		while (arg9.hasNext()) {
			Object o = arg9.next();
			Map<?, ?> map = (Map<?, ?>) o;
			resultList.add(new MovimentacaoCategoria((String) map.get("nome"),
					((Double) map.get("valor")).doubleValue(), ((Integer) map.get("mes")).intValue(),
					((Integer) map.get("ano")).intValue(), (String) map.get("tipo")));
		}

		return resultList;
	}

	public List<MovimentacaoAnual> movimentacaoUltimos12Meses(ParcelaMovimentacaoFilter filter) {
		String sql = "select * from tipo_status_total_mensal  where data <= :date order by data desc limit 48";
		ArrayList<MovimentacaoAnual> resultList = new ArrayList<>();
		Session session = (Session) this.entityManager.unwrap(Session.class);
		SQLQuery query = session.createSQLQuery(sql);
		int mes = Integer.valueOf(filter.getMes()).intValue();
		int ano = Integer.valueOf(filter.getAno()).intValue();
		int dia = DataUtil.ultimoDiaDoMes(DataUtil.stringParaDate("01/" + mes + "/" + ano));
		query.setParameter("date", DataUtil.stringParaDate(dia + "/" + mes + "/" + ano));
		query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
		List<?> data = query.list();
		Iterator<?> arg10 = data.iterator();

		while (arg10.hasNext()) {
			Object o = arg10.next();
			Map<?, ?> map = (Map<?, ?>) o;
			resultList.add(new MovimentacaoAnual((String) map.get("tipo"), (String) map.get("status"),
					(String) map.get("mes") + "/" + (String) map.get("ano"),
					((Double) map.get("valor")).doubleValue()));
		}

		return resultList;
	}
}