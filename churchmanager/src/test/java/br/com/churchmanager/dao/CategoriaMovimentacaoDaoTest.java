package br.com.churchmanager.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.util.List;

import javax.persistence.EntityManager;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import br.com.churchmanager.builder.CategoriaMovimentacaoBuilder;
import br.com.churchmanager.exception.ViolacaoDeRestricaoException;
import br.com.churchmanager.model.CategoriaMovimentacao;
import br.com.churchmanager.model.Status;
import br.com.churchmanager.repository.CategoriaMovimentacaoRepository;
import br.com.churchmanager.util.JPAUtil;

public class CategoriaMovimentacaoDaoTest {

	private static final boolean DISTINCT_TRUE = true;
	private static final boolean ASC = true;

	private EntityManager entityManager;
	private CategoriaMovimentacaoRepository categoriaRepository;
	private CategoriaMovimentacao categoria;

	@Rule
	public ExpectedException expectedException = ExpectedException.none();

	private CategoriaMovimentacao novaCategoria() {
		CategoriaMovimentacao categoria = new CategoriaMovimentacaoBuilder().comNome("Material de limpeza")
				.comDescricao("Descrição da categoria").ativo().build();
		return categoria;
	}

	@Before
	public void setUp() {
		entityManager = new JPAUtil().getEntityManager();
		entityManager.getTransaction().begin();
		categoriaRepository = new CategoriaMovimentacaoRepository(entityManager);
		categoria = novaCategoria();
	}

	@After
	public void tearDown() {
		entityManager.getTransaction().rollback();
		entityManager.close();
		categoriaRepository = null;
		categoria = null;
	}

	@Test
	public void deveCadastrarCategoria() {

		long qtdRegistros = categoriaRepository.contagem("id", null, null, DISTINCT_TRUE);
		categoriaRepository.save(categoria);
		long totalAtualRegistros = categoriaRepository.contagem("id", null, null, DISTINCT_TRUE);

		CategoriaMovimentacao categoriaDoBanco = categoriaRepository.findBy(categoria.getId());

		assertEquals(qtdRegistros + 1, totalAtualRegistros);
		assertEquals("id", categoria.getId(), categoriaDoBanco.getId());
		assertEquals("nome", categoria.getNome(), categoriaDoBanco.getNome());
		assertEquals("status", categoria.getStatus(), categoriaDoBanco.getStatus());
		assertEquals("descricao", categoria.getDescricao(), categoriaDoBanco.getDescricao());

	}

	@Test
	public void deveEditarCategoria() {
		categoriaRepository.save(categoria);

		categoria.setNome("Mercantil");
		categoria.setDescricao("Uma nova descrição");

		categoriaRepository.save(categoria);

		CategoriaMovimentacao categoriaDoBanco = categoriaRepository.buscarPorAtributo("nome", categoria.getNome());

		assertEquals("id", categoria.getId(), categoriaDoBanco.getId());
		assertEquals("nome", categoria.getNome(), categoriaDoBanco.getNome());
		assertEquals("status", categoria.getStatus(), categoriaDoBanco.getStatus());
		assertEquals("descricao", categoria.getDescricao(), categoriaDoBanco.getDescricao());

	}

	@Test
	public void deveRemoverCategoria() {
		categoriaRepository.save(categoria);
		long qtdRegistros = categoriaRepository.contagem("id", null, null, DISTINCT_TRUE);
		categoriaRepository.remove(categoria);
		long totalAtualRegistros = categoriaRepository.contagem("id", null, null, DISTINCT_TRUE);
		categoria = categoriaRepository.findBy(categoria.getId());
		assertNull(categoria);
		assertEquals(qtdRegistros - 1, totalAtualRegistros);
	}

	@Test
	public void deveListarCategoriasOrdenandoPorNome() {

		CategoriaMovimentacao materiaLimpeza = new CategoriaMovimentacaoBuilder().comNome("Material de limpeza")
				.comDescricao("Descrição da categoria").ativo().build();
		CategoriaMovimentacao mercantil = new CategoriaMovimentacaoBuilder().comNome("Mercantil")
				.comDescricao("Descrição da categoria").ativo().build();
		CategoriaMovimentacao prebenda = new CategoriaMovimentacaoBuilder().comNome("Prebenda")
				.comDescricao("Descrição da categoria").ativo().build();
		CategoriaMovimentacao darAula = new CategoriaMovimentacaoBuilder().comNome("Equipamento de som")
				.comDescricao("Descrição da categoria").ativo().build();

		categoriaRepository.save(darAula);
		categoriaRepository.save(materiaLimpeza);
		categoriaRepository.save(mercantil);
		categoriaRepository.save(prebenda);

		List<CategoriaMovimentacao> categorias = categoriaRepository.findAll(ASC, "nome");
		int tamanhoDaLista = categorias.size();

		assertEquals(4, tamanhoDaLista);
		assertEquals(darAula.getNome(), categorias.get(0).getNome());
		assertEquals(materiaLimpeza.getNome(), categorias.get(1).getNome());
		assertEquals(mercantil.getNome(), categorias.get(2).getNome());
		assertEquals(prebenda.getNome(), categorias.get(3).getNome());

	}

	@Test
	public void deveBuscarCategoriaPorIdentificador() {

		categoriaRepository.save(categoria);
		CategoriaMovimentacao categoriaDoBanco = categoriaRepository.findBy(categoria.getId());

		assertEquals("id", categoria.getId(), categoriaDoBanco.getId());
		assertEquals("nome", categoria.getNome(), categoriaDoBanco.getNome());
		assertEquals("status", categoria.getStatus(), categoriaDoBanco.getStatus());
		assertEquals("descricao", categoria.getDescricao(), categoriaDoBanco.getDescricao());

	}

	@Test
	public void deveEncontrarCategoriaPorNome() {

		categoriaRepository.save(categoria);
		CategoriaMovimentacao categoriaDoBanco = categoriaRepository.buscarPorAtributo("nome", "Material de limpeza");

		assertEquals("id", categoria.getId(), categoriaDoBanco.getId());
		assertEquals("nome", categoria.getNome(), categoriaDoBanco.getNome());
		assertEquals("status", categoria.getStatus(), categoriaDoBanco.getStatus());
		assertEquals("descricao", categoria.getDescricao(), categoriaDoBanco.getDescricao());
	}

	@Test
	public void deveRetornarNuloSeNaoEncontrarCategoriaPorNome() {
		CategoriaMovimentacao categoriaDoBanco = categoriaRepository.buscarPorAtributo("nome", "Mercantil");
		assertNull(categoriaDoBanco);
	}

	@Test
	public void naoDeveTerNomeDuplicado() {
		expectedException.expect(ViolacaoDeRestricaoException.class);

		CategoriaMovimentacao cantar1 = new CategoriaMovimentacaoBuilder().comNome("Mercantil")
				.comDescricao("Descrição da categoria").ativo().build();
		CategoriaMovimentacao cantar2 = new CategoriaMovimentacaoBuilder().comNome("Mercantil")
				.comDescricao("Descrição da categoria").ativo().build();

		categoriaRepository.save(cantar1);
		categoriaRepository.save(cantar2);

	}

	@Test
	public void deveAlterarStatus() {
		CategoriaMovimentacao cantar = new CategoriaMovimentacaoBuilder().comNome("Mercantil")
				.comDescricao("Descrição da categoria").ativo().build();
		CategoriaMovimentacao tocarGuitarra = new CategoriaMovimentacaoBuilder().comNome("Material de limpeza")
				.comDescricao("Descrição da categoria").inativo().build();

		categoriaRepository.save(cantar);
		categoriaRepository.save(tocarGuitarra);

		categoriaRepository.saveStatus(cantar);
		categoriaRepository.saveStatus(tocarGuitarra);

		CategoriaMovimentacao categoriaBanco1 = categoriaRepository.findBy(cantar.getId());
		CategoriaMovimentacao categoriaBanco2 = categoriaRepository.findBy(tocarGuitarra.getId());

		assertEquals(Status.INATIVO, categoriaBanco1.getStatus());
		assertEquals(Status.ATIVO, categoriaBanco2.getStatus());

	}

}
