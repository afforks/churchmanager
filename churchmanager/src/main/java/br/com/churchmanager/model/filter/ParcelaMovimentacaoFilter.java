package br.com.churchmanager.model.filter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Restrictions;

import br.com.churchmanager.dao.generic.Alias;
import br.com.churchmanager.model.Status;
import br.com.churchmanager.model.StatusMovimentacao;
import br.com.churchmanager.model.TipoMovimentacao;
import br.com.churchmanager.util.DataUtil;

public class ParcelaMovimentacaoFilter implements Filter, Serializable {

	private static final long serialVersionUID = 5321701463701329577L;

	private String mes;
	private String ano;
	private String nome;
	private StatusMovimentacao statusMovimentacao;
	private TipoMovimentacao tipoMovimentacao;

	public List<Criterion> restricoes() {
		ArrayList<Criterion> restricoes = new ArrayList<>();
		if (StringUtils.isNotBlank(this.getNome())) {
			restricoes.add(Restrictions.like("nome", this.getNome(), MatchMode.ANYWHERE));
		}

		if (StringUtils.isNotBlank(this.getMes()) && StringUtils.isNotBlank(this.getAno())) {
			Date data1 = DataUtil.stringParaDate("01/" + this.mes + "/" + this.ano);
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(data1);
			int ultimoDiaDoMes = calendar.getActualMaximum(5);
			Date data2 = DataUtil.stringParaDate(ultimoDiaDoMes + "/" + this.mes + "/" + this.ano);
			restricoes.add(Restrictions.between("dataVencimento", data1, data2));
		}

		if (this.getTipoMovimentacao() != null) {
			restricoes.add(Restrictions.eq("movimentacao.tipoMovimentacao", this.getTipoMovimentacao()));
		}

		if (this.getStatusMovimentacao() != null) {
			restricoes.add(Restrictions.eq("statusMovimentacao", this.getStatusMovimentacao()));
		}

		restricoes.add(Restrictions.eq("status", Status.ATIVO));
		return restricoes;
	}

	public List<Projection> projecoes() {
		return new ArrayList<>();
	}

	public List<Alias> aliases() {
		ArrayList<Alias> aliases = new ArrayList<>();
		aliases.add(new Alias("movimentacao", "movimentacao"));
		return aliases;
	}

	public boolean usarDistinct() {
		return false;
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

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public StatusMovimentacao getStatusMovimentacao() {
		return statusMovimentacao;
	}

	public void setStatusMovimentacao(StatusMovimentacao statusMovimentacao) {
		this.statusMovimentacao = statusMovimentacao;
	}

	public TipoMovimentacao getTipoMovimentacao() {
		return tipoMovimentacao;
	}

	public void setTipoMovimentacao(TipoMovimentacao tipoMovimentacao) {
		this.tipoMovimentacao = tipoMovimentacao;
	}

}