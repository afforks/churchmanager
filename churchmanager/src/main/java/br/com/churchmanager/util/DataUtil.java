package br.com.churchmanager.util;

import static java.util.Calendar.DATE;
import static java.util.Calendar.MONTH;
import static java.util.Calendar.YEAR;
import static java.util.Calendar.getInstance;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class DataUtil implements Serializable {

	private static final long serialVersionUID = -6894001147547342101L;

	public static String dateParaString(Date data) {
		return (new SimpleDateFormat("dd/MM/yyyy")).format(data);
	}

	public static Date stringParaDate(String data) {
		Date date = null;
		try {
			date = (new SimpleDateFormat("dd/MM/yyyy")).parse(data);
		} catch (ParseException arg2) {
			arg2.printStackTrace();
		}
		return date;
	}

	public static Date gerarDataParaParcelamento(Date data) {
		Calendar c = getInstance();
		c.setTime(data);
		c.add(MONTH, 1);
		return c.getTime();
	}

	public static Date gerarDataParaParcelamento(Date data, int quantidadeDeMeses) {
		Calendar c = getInstance();
		c.setTime(data);
		c.add(MONTH, quantidadeDeMeses);
		return c.getTime();
	}

	public static Meses[] getMeses() {
		return Meses.values();
	}

	public static List<Meses> getListMeses() {
		return Arrays.asList(Meses.values());
	}

	public static List<Integer> getAnos() {
		Integer ano = Integer.valueOf(getInstance().get(1));
		Integer anoProx = Integer.valueOf(ano.intValue() + 1);
		Integer anoPrev = Integer.valueOf(ano.intValue() - 1);
		return Arrays.asList(new Integer[] { anoPrev, ano, anoProx });
	}
	
	public static List<Integer> getAnos(String year) {
		Integer ano = Integer.valueOf(year);
		Integer anoProx = Integer.valueOf(ano.intValue() + 1);
		Integer anoPrev = Integer.valueOf(ano.intValue() - 1);
		return Arrays.asList(new Integer[] { anoPrev, ano, anoProx });
	}

	public static String mes() {
		return (new SimpleDateFormat("MM")).format(new Date());
	}

	public static String ano() {
		return (new SimpleDateFormat("yyyy")).format(new Date());
	}

	public static int calcularIdade(Date dataNascimento) {

		Calendar c = getInstance();
		Calendar c2 = getInstance();
		Date dataAtual = new Date();
		c.setTime(dataNascimento);
		c2.setTime(dataAtual);
		int diaAtual = c2.get(DATE);
		int mesAtual = c2.get(MONTH);
		int anoAtual = c2.get(YEAR);
		int idade = anoAtual - c.get(YEAR) - 1;
		if (mesAtual >= c.get(MONTH) && diaAtual >= c.get(DATE)) {
			++idade;
		}

		return idade;
	}

	public static int primeiroDiaDoMes(Date data) {
		Calendar c = getInstance();
		c.setTime(data);
		return c.getActualMinimum(DATE);
	}

	public static int ultimoDiaDoMes(Date data) {
		Calendar c = getInstance();
		c.setTime(data);
		return c.getActualMaximum(DATE);
	}

	public static Date mesAnterior(Date data) {
		Calendar c = getInstance();
		c.setTime(data);
		c.add(MONTH, -1);
		data = c.getTime();
		return data;
	}

	public static String semestre() {
		return Integer.valueOf(mes()) < 7 ? "01" : "02";
	}
}