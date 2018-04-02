package br.com.churchmanager.util;

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
		Calendar c = Calendar.getInstance();
		c.setTime(data);
		c.add(2, 1);
		return c.getTime();
	}

	public static Date gerarDataParaParcelamento(Date data, int quantidadeDeMeses) {
		Calendar c = Calendar.getInstance();
		c.setTime(data);
		c.add(2, quantidadeDeMeses);
		return c.getTime();
	}

	public static Meses[] getMeses() {
		return Meses.values();
	}

	public static List<Meses> getListMeses() {
		return Arrays.asList(Meses.values());
	}

	public static List<Integer> getAnos() {
		Integer ano = Integer.valueOf(Calendar.getInstance().get(1));
		Integer anoPrev = Integer.valueOf(ano.intValue() - 1);
		Integer anoPrevPrev = Integer.valueOf(anoPrev.intValue() - 1);
		return Arrays.asList(new Integer[] { anoPrevPrev, anoPrev, ano });
	}

	public static String mes() {
		return (new SimpleDateFormat("MM")).format(new Date());
	}

	public static String ano() {
		return (new SimpleDateFormat("yyyy")).format(new Date());
	}

	public static int calcularIdade(Date dataNascimento) {
		boolean idade = false;
		Calendar c = Calendar.getInstance();
		Calendar c2 = Calendar.getInstance();
		Date dataAtual = new Date();
		c.setTime(dataNascimento);
		c2.setTime(dataAtual);
		int diaAtual = c2.get(Calendar.DATE);
		int mesAtual = c2.get(Calendar.MONTH);
		int anoAtual = c2.get(Calendar.YEAR);
		int arg7 = anoAtual - c.get(Calendar.YEAR) - 1;
		if (mesAtual >= c.get(Calendar.MONTH) && diaAtual >= c.get(Calendar.DATE)) {
			++arg7;
		}

		return arg7;
	}

	public static int primeiroDiaDoMes(Date data) {
		Calendar c = Calendar.getInstance();
		c.setTime(data);
		return c.getActualMinimum(Calendar.DATE);
	}

	public static int ultimoDiaDoMes(Date data) {
		Calendar c = Calendar.getInstance();
		c.setTime(data);
		return c.getActualMaximum(Calendar.DATE);
	}

	public static Date mesAnterior(Date data) {
		Calendar c = Calendar.getInstance();
		c.setTime(data);
		c.add(2, -1);
		data = c.getTime();
		return data;
	}
	
	public static String semestre() {
		return Integer.valueOf(mes()) < 7 ? "01" : "02";
	}
}