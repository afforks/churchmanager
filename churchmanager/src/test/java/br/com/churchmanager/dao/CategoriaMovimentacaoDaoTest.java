package br.com.churchmanager.dao;

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
import br.com.churchmanager.util.JPAUtil;

public class CategoriaMovimentacaoDaoTest {

	private static final boolean DISTINCT_TRUE = true;
	private static final boolean ASC = true;

	private EntityManager entityManager;
	private CategoriaMovimentacaoDAO categoriaDAO;
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
		categoriaDAO = new CategoriaMovimentacaoDAO(entityManager);
		categoria = novaCategoria();
	}

	@After
	public void tearDown() {
		entityManager.getTransaction().rollback();
		entityManager.close();
		categoriaDAO = null;
		categoria = null;
	}

	@Test
	public void deveCadastrarCategoria() {

		long qtdRegistros = categoriaDAO.contagem("id", null, null, DISTINCT_TRUE);
		categoriaDAO.salvar(categoria);
		long totalAtualRegistros = categoriaDAO.contagem("id", null, null, DISTINCT_TRUE);

		CategoriaMovimentacao categoriaDoBanco = categoriaDAO.buscarPorId(categoria.getId());

		assertEquals(qtdRegistros + 1, totalAtualRegistros);
		assertEquals("id", categoria.getId(), categoriaDoBanco.getId());
		assertEquals("nome", categoria.getNome(), categoriaDoBanco.getNome());
		assertEquals("status", categoria.getStatus(), categoriaDoBanco.getStatus());
		assertEquals("descricao", categoria.getDescricao(), categoriaDoBanco.getDescricao());

	}

	@Test
	public void deveEditarCategoria() {
		categoriaDAO.salvar(categoria);

		categoria.setNome("Mercantil");
		categoria.setDescricao("Uma nova descrição");

		categoriaDAO.atualizar(categoria);

		CategoriaMovimentacao categoriaDoBanco = categoriaDAO.buscarPorAtributo("nome", categoria.getNome());

		assertEquals("id", categoria.getId(), categoriaDoBanco.getId());
		assertEquals("nome", categoria.getNome(), categoriaDoBanco.getNome());
		assertEquals("status", categoria.getStatus(), categoriaDoBanco.getStatus());
		assertEquals("descricao", categoria.getDescricao(), categoriaDoBanco.getDescricao());

	}

	@Test
	public void deveRemoverCategoria() {
		categoriaDAO.salvar(categoria);
		long qtdRegistros = categoriaDAO.contagem("id", null, null, DISTINCT_TRUE);
		categoriaDAO.excluir(categoria);
		long totalAtualRegistros = categoriaDAO.contagem("id", null, null, DISTINCT_TRUE);
		categoria = categoriaDAO.buscarPorId(categoria.getId());
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

		categoriaDAO.salvar(darAula);
		categoriaDAO.salvar(materiaLimpeza);
		categoriaDAO.salvar(mercantil);
		categoriaDAO.salvar(prebenda);

		List<CategoriaMovimentacao> categorias = categoriaDAO.listar(ASC, "nome");
		int tamanhoDaLista = categorias.size();

		assertEquals(4, tamanhoDaLista);
		assertEquals(darAula.getNome(), categorias.get(0).getNome());
		assertEquals(materiaLimpeza.getNome(), categorias.get(1).getNome());
		assertEquals(mercantil.getNome(), categorias.get(2).getNome());
		assertEquals(prebenda.getNome(), categorias.get(3).getNome());

	}

	@Test
	public void deveBuscarCategoriaPorIdentificador() {

		categoriaDAO.salvar(categoria);
		CategoriaMovimentacao categoriaDoBanco = categoriaDAO.buscarPorId(categoria.getId());

		assertEquals("id", categoria.getId(), categoriaDoBanco.getId());
		assertEquals("nome", categoria.getNome(), categoriaDoBanco.getNome());
		assertEquals("status", categoria.getStatus(), categoriaDoBanco.getStatus());
		assertEquals("descricao", categoria.getDescricao(), categoriaDoBanco.getDescricao());

	}

	@Test
	public void deveEncontrarCategoriaPorNome() {

		categoriaDAO.salvar(categoria);
		CategoriaMovimentacao categoriaDoBanco = categoriaDAO.buscarPorAtributo("nome", "Material de limpeza");

		assertEquals("id", categoria.getId(), categoriaDoBanco.getId());
		assertEquals("nome", categoria.getNome(), categoriaDoBanco.getNome());
		assertEquals("status", categoria.getStatus(), categoriaDoBanco.getStatus());
		assertEquals("descricao", categoria.getDescricao(), categoriaDoBanco.getDescricao());
	}

	@Test
	public void deveRetornarNuloSeNaoEncontrarCategoriaPorNome() {
		CategoriaMovimentacao categoriaDoBanco = categoriaDAO.buscarPorAtributo("Mercantil");
		assertNull(categoriaDoBanco);
	}

	@Test
	public void naoDeveTerNomeDuplicado() {
		expectedException.expect(ViolacaoDeRestricaoException.class);

		CategoriaMovimentacao cantar1 = new CategoriaMovimentacaoBuilder().comNome("Mercantil")
				.comDescricao("Descrição da categoria").ativo().build();
		CategoriaMovimentacao cantar2 = new CategoriaMovimentacaoBuilder().comNome("Mercantil")
				.comDescricao("Descrição da categoria").ativo().build();

		categoriaDAO.salvar(cantar1);
		categoriaDAO.salvar(cantar2);

	}

	@Test
	public void deveAlterarStatus() {
		CategoriaMovimentacao cantar = new CategoriaMovimentacaoBuilder().comNome("Mercantil")
				.comDescricao("Descrição da categoria").ativo().build();
		CategoriaMovimentacao tocarGuitarra = new CategoriaMovimentacaoBuilder().comNome("Material de limpeza")
				.comDescricao("Descrição da categoria").inativo().build();

		categoriaDAO.salvar(cantar);
		categoriaDAO.salvar(tocarGuitarra);

		categoriaDAO.atualizarStatus(cantar);
		categoriaDAO.atualizarStatus(tocarGuitarra);

		CategoriaMovimentacao categoriaBanco1 = categoriaDAO.buscarPorId(cantar.getId());
		CategoriaMovimentacao categoriaBanco2 = categoriaDAO.buscarPorId(tocarGuitarra.getId());

		assertEquals(Status.INATIVO, categoriaBanco1.getStatus());
		assertEquals(Status.ATIVO, categoriaBanco2.getStatus());

	}

}
