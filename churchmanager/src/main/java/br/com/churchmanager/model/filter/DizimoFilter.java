package br.com.churchmanager.model.filter;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Restrictions;

import br.com.churchmanager.generic.dao.Alias;
import br.com.churchmanager.util.DataUtil;

public class DizimoFilter implements Filter {
	private String nomePessoa;
	private double valorDizimo;
	private double valorOferta;
	private Date dataReferencia;
	private Date dataRecebimento;
	private String mes;
	private String ano;

	public List<Criterion> restricoes() {
		ArrayList<Criterion> restricoes = new ArrayList<>();
		if (StringUtils.isNotBlank(this.getMes()) && StringUtils.isNotBlank(this.getAno())) {
			Date data1 = DataUtil.stringParaDate("01/" + this.mes + "/" + this.ano);
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(data1);
			int ultimoDiaDoMes = calendar.getActualMaximum(5);
			Date data2 = DataUtil.stringParaDate(ultimoDiaDoMes + "/" + this.mes + "/" + this.ano);
			restricoes.add(Restrictions.between("dataReferencia", data1, data2));
		}

		return restricoes;
	}

	public List<Projection> projecoes() {
		return null;
	}

	public List<Alias> aliases() {
		return null;
	}

	public Boolean usarDistinct() {
		return null;
	}

	public String getNomePessoa() {
		return this.nomePessoa;
	}

	public void setNomePessoa(String nomePessoa) {
		this.nomePessoa = nomePessoa;
	}

	public double getValorDizimo() {
		return this.valorDizimo;
	}

	public void setValorDizimo(double valorDizimo) {
		this.valorDizimo = valorDizimo;
	}

	public double getValorOferta() {
		return this.valorOferta;
	}

	public void setValorOferta(double valorOferta) {
		this.valorOferta = valorOferta;
	}

	public Date getDataReferencia() {
		return this.dataReferencia;
	}

	public void setDataReferencia(Date dataReferencia) {
		this.dataReferencia = dataReferencia;
	}

	public Date getDataRecebimento() {
		return this.dataRecebimento;
	}

	public void setDataRecebimento(Date dataRecebimento) {
		this.dataRecebimento = dataRecebimento;
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