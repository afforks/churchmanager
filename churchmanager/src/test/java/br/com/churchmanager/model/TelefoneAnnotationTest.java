package br.com.churchmanager.model;

import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import br.com.churchmanager.util.custom.validator.TelefoneValidator;

public class TelefoneAnnotationTest {

	private TelefoneValidator telefoneValidator;

	@Before
	public void setUp() {
		this.telefoneValidator = new TelefoneValidator();
	}

	@After
	public void tearDown() {
		this.telefoneValidator = null;
	}
	
	@Test
	public void deveValidarTelfoneEmBranco() {
		assertTrue(telefoneValidator.isValid("", null));
	}

	@Test
	public void deveValidarTelefoneNulo() {
		assertTrue(telefoneValidator.isValid(null, null));
	}

	@Test
	public void deveValidarTelefone() {
		assertTrue(telefoneValidator.isValid("(88)1234-5678", null));
	}

	@Test
	public void deveValidarTelefoneDigitoExtra() {
		assertTrue(telefoneValidator.isValid("(88)91234-5678", null));
	}

	@Test
	public void naoDeveValidarTelefone() {
		assertTrue(!telefoneValidator.isValid("(8)12345-7894", null));
		assertTrue(!telefoneValidator.isValid("(088)12345-7894", null));
		assertTrue(!telefoneValidator.isValid("88 12345-7894", null));
		assertTrue(!telefoneValidator.isValid("(88)123457894", null));
		assertTrue(!telefoneValidator.isValid("(88) 12345-7894", null));
	}

}
