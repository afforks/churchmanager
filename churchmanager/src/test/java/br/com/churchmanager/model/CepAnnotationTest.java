package br.com.churchmanager.model;

import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import br.com.churchmanager.util.custom.validator.CepValidator;

public class CepAnnotationTest {

	private CepValidator cepValidator;

	@Before
	public void setUp() {
		this.cepValidator = new CepValidator();
	}

	@After
	public void tearDown() {
		this.cepValidator = null;
	}

	@Test
	public void deveValidarCep() {
		assertTrue(cepValidator.isValid("62.940-000", null));
		assertTrue(cepValidator.isValid("63.900-000", null));
		assertTrue(cepValidator.isValid("72.210-096", null));
		assertTrue(cepValidator.isValid("59.147-385", null));
		assertTrue(cepValidator.isValid("79.112-330", null));
	}

	@Test
	public void naoDeveValidarCep() {
		assertTrue(!cepValidator.isValid("62940-000", null));
		assertTrue(!cepValidator.isValid("63.900000", null));
		assertTrue(!cepValidator.isValid("72210096", null));
		assertTrue(!cepValidator.isValid("59.1747-385", null));
		assertTrue(!cepValidator.isValid("79.12-330", null));
	}

}
