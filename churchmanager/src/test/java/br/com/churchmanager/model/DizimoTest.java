package br.com.churchmanager.model;

import org.junit.Assert;
import org.junit.Test;

import br.com.churchmanager.builder.DizimoBuilder;
import br.com.churchmanager.model.Dizimo;

public class DizimoTest {

	@Test
	public void deveCalcularValorTotal() {
		Dizimo dizimo = new DizimoBuilder().comValorOferta(115.50).comValorDizimo(10.20).build();
		Assert.assertEquals(125.70, dizimo.getValorTotal().doubleValue(), 0.0001);
	}

}
