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
import br.com.churchmanager.util.DataUtil;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DizimoFilter implements Filter, Serializable {

	private static final long serialVersionUID = -5696573251550574369L;

	private String nomePessoa;
	private double valorDizimo;
	private double valorOferta;
	private Date dataReferencia;
	private Date dataRecebimento;
	private String mes;
	private String ano;

	public List<Criterion> restricoes() {
		ArrayList<Criterion> restricoes = new ArrayList<>();
		if (StringUtils.isNotBlank(this.getNomePessoa())) {
			restricoes.add(Restrictions.like("pessoa.nome", this.getNomePessoa(), MatchMode.ANYWHERE));
		}

		if (StringUtils.isNotBlank(this.getMes()) && StringUtils.isNotBlank(this.getAno())) {
			Date data1 = DataUtil.stringParaDate("01/" + this.mes + "/" + this.ano);
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(data1);
			int ultimoDiaDoMes = calendar.getActualMaximum(5);
			Date data2 = DataUtil.stringParaDate(ultimoDiaDoMes + "/" + this.mes + "/" + this.ano);
			restricoes.add(Restrictions.between("dataReferencia", data1, data2));
		}
		restricoes.add(Restrictions.eq("status", Status.ATIVO));
		return restricoes;
	}

	public List<Projection> projecoes() {
		return new ArrayList<>();
	}

	public List<Alias> aliases() {
		List<Alias> aliases = new ArrayList<>();
		aliases.add(new Alias("pessoa", "pessoa"));
		return aliases;
	}

	public boolean usarDistinct() {
		return false;
	}
}