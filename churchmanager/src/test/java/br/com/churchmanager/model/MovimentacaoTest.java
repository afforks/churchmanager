package br.com.churchmanager.model;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import br.com.churchmanager.builder.MovimentacaoBuilder;
import br.com.churchmanager.exception.NumeroParcelasInvalidoException;
import br.com.churchmanager.exception.ValorInvalidoException;

public class MovimentacaoTest {

	@Rule()
	public ExpectedException exception = ExpectedException.none();

	@Test
	public void deveCalcularValorDaParcela() {
		Movimentacao movimentacao = null;
		movimentacao = new MovimentacaoBuilder().comParcelas(5).comValor(127.50f).build();
		movimentacao.gerarParcelas();
		Assert.assertEquals(25.50, movimentacao.getParcelas().get(0).getValorParcela(), 0.0001);
	}

	@Test
	public void deveValidarNumeroDeParcelasMaiorQueZero() {
		exception.expect(NumeroParcelasInvalidoException.class);
		exception.expectMessage("O número de parcelas deve ser maior que zero!");
		new MovimentacaoBuilder().comParcelas(0).comValor(127.50f).build();
	}

	@Test
	public void deveValidarValorMaiorQueZero() {
		exception.expect(ValorInvalidoException.class);
		exception.expectMessage("O valor deve ser maior que zero!");
		new MovimentacaoBuilder().comParcelas(0).comValor(127.50f).build();
	}
}
