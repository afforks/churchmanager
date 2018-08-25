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

import br.com.churchmanager.builder.AtividadeEclesiasticaBuilder;
import br.com.churchmanager.exception.ViolacaoDeRestricaoException;
import br.com.churchmanager.model.AtividadeEclesiastica;
import br.com.churchmanager.model.Status;
import br.com.churchmanager.util.JPAUtil;

public class AtividadeEclesiasticaDaoTest {

	private static final boolean DISTINCT_TRUE = true;
	private static final boolean ASC = true;

	private EntityManager entityManager;
	private AtividadeEclesiasticaDAO atividadeDAO;
	private AtividadeEclesiastica atividade;

	@Rule
	public ExpectedException expectedException = ExpectedException.none();

	private AtividadeEclesiastica novaAtividade() {
		AtividadeEclesiastica atividade = new AtividadeEclesiasticaBuilder().comNome("Tocar guitarra")
				.comDescricao("Descrição da atividade").ativo().build();
		return atividade;
	}

	@Before
	public void setUp() {
		entityManager = new JPAUtil().getEntityManager();
		entityManager.getTransaction().begin();
		atividadeDAO = new AtividadeEclesiasticaDAO(entityManager);
		atividade = novaAtividade();
	}

	@After
	public void tearDown() {
		entityManager.getTransaction().rollback();
		entityManager.close();
		atividadeDAO = null;
		atividade = null;
	}

	@Test
	public void deveCadastrarAtividade() {

		long qtdRegistros = atividadeDAO.contagem("id", null, null, DISTINCT_TRUE);
		atividadeDAO.salvar(atividade);
		long totalAtualRegistros = atividadeDAO.contagem("id", null, null, DISTINCT_TRUE);

		AtividadeEclesiastica atividadeDoBanco = atividadeDAO.buscarPorId(atividade.getId());

		assertEquals(qtdRegistros + 1, totalAtualRegistros);
		assertEquals("id", atividade.getId(), atividadeDoBanco.getId());
		assertEquals("nome", atividade.getNome(), atividadeDoBanco.getNome());
		assertEquals("status", atividade.getStatus(), atividadeDoBanco.getStatus());
		assertEquals("descricao", atividade.getDescricao(), atividadeDoBanco.getDescricao());

	}

	@Test
	public void deveEditarAtividade() {
		atividadeDAO.salvar(atividade);

		atividade.setNome("Cantar");
		atividade.setDescricao("Uma nova descrição");

		atividadeDAO.atualizar(atividade);

		AtividadeEclesiastica atividadeDoBanco = atividadeDAO.buscarPorAtributo("nome", atividade.getNome());

		assertEquals("id", atividade.getId(), atividadeDoBanco.getId());
		assertEquals("nome", atividade.getNome(), atividadeDoBanco.getNome());
		assertEquals("status", atividade.getStatus(), atividadeDoBanco.getStatus());
		assertEquals("descricao", atividade.getDescricao(), atividadeDoBanco.getDescricao());

	}

	@Test
	public void deveRemoverAtividade() {
		atividadeDAO.salvar(atividade);
		long qtdRegistros = atividadeDAO.contagem("id", null, null, DISTINCT_TRUE);
		atividadeDAO.excluir(atividade);
		long totalAtualRegistros = atividadeDAO.contagem("id", null, null, DISTINCT_TRUE);
		atividade = atividadeDAO.buscarPorId(atividade.getId());
		assertNull(atividade);
		assertEquals(qtdRegistros - 1, totalAtualRegistros);
	}

	@Test
	public void deveListarAtividadesOrdenandoPorNome() {

		AtividadeEclesiastica tocarGuitarra = new AtividadeEclesiasticaBuilder().comNome("Tocar guitarra")
				.comDescricao("Descrição da atividade").ativo().build();
		AtividadeEclesiastica cantar = new AtividadeEclesiasticaBuilder().comNome("Cantar")
				.comDescricao("Descrição da atividade").ativo().build();
		AtividadeEclesiastica recepcionarVisitantes = new AtividadeEclesiasticaBuilder()
				.comNome("Recepcionar visitantes").comDescricao("Descrição da atividade").ativo().build();
		AtividadeEclesiastica darAula = new AtividadeEclesiasticaBuilder().comNome("Dar aulas")
				.comDescricao("Descrição da atividade").ativo().build();

		atividadeDAO.salvar(tocarGuitarra);
		atividadeDAO.salvar(cantar);
		atividadeDAO.salvar(darAula);
		atividadeDAO.salvar(recepcionarVisitantes);

		List<AtividadeEclesiastica> atividades = atividadeDAO.listar(ASC, "nome");
		int tamanhoDaLista = atividades.size();

		assertEquals(4, tamanhoDaLista);
		assertEquals(cantar.getNome(), atividades.get(0).getNome());
		assertEquals(darAula.getNome(), atividades.get(1).getNome());
		assertEquals(recepcionarVisitantes.getNome(), atividades.get(2).getNome());
		assertEquals(tocarGuitarra.getNome(), atividades.get(3).getNome());

	}

	@Test
	public void deveBuscarAtividadePorIdentificador() {

		atividadeDAO.salvar(atividade);
		AtividadeEclesiastica atividadeDoBanco = atividadeDAO.buscarPorId(atividade.getId());

		assertEquals("id", atividade.getId(), atividadeDoBanco.getId());
		assertEquals("nome", atividade.getNome(), atividadeDoBanco.getNome());
		assertEquals("status", atividade.getStatus(), atividadeDoBanco.getStatus());
		assertEquals("descricao", atividade.getDescricao(), atividadeDoBanco.getDescricao());

	}

	@Test
	public void deveEncontrarAtividadePorNome() {

		atividadeDAO.salvar(atividade);
		AtividadeEclesiastica atividadeDoBanco = atividadeDAO.buscarPorAtributo("nome", "Tocar guitarra");

		assertEquals("id", atividade.getId(), atividadeDoBanco.getId());
		assertEquals("nome", atividade.getNome(), atividadeDoBanco.getNome());
		assertEquals("status", atividade.getStatus(), atividadeDoBanco.getStatus());
		assertEquals("descricao", atividade.getDescricao(), atividadeDoBanco.getDescricao());
	}

	@Test
	public void deveRetornarNuloSeNaoEncontrarAtividadePorNome() {
		AtividadeEclesiastica atividadeDoBanco = atividadeDAO.buscarPorAtributo("nome", "Cantar");
		assertNull(atividadeDoBanco);
	}

	@Test
	public void naoDeveTerNomeDuplicado() {
		expectedException.expect(ViolacaoDeRestricaoException.class);

		AtividadeEclesiastica cantar1 = new AtividadeEclesiasticaBuilder().comNome("Cantar")
				.comDescricao("Descrição da atividade").ativo().build();
		AtividadeEclesiastica cantar2 = new AtividadeEclesiasticaBuilder().comNome("Cantar")
				.comDescricao("Descrição da atividade").ativo().build();

		atividadeDAO.salvar(cantar1);
		atividadeDAO.salvar(cantar2);

	}

	@Test
	public void deveAlterarStatus() {
		AtividadeEclesiastica cantar = new AtividadeEclesiasticaBuilder().comNome("Cantar")
				.comDescricao("Descrição da atividade").ativo().build();
		AtividadeEclesiastica tocarGuitarra = new AtividadeEclesiasticaBuilder().comNome("Tocar Guitarra")
				.comDescricao("Descrição da atividade").inativo().build();

		atividadeDAO.salvar(cantar);
		atividadeDAO.salvar(tocarGuitarra);

		atividadeDAO.atualizarStatus(cantar);
		atividadeDAO.atualizarStatus(tocarGuitarra);

		AtividadeEclesiastica atividadeBanco1 = atividadeDAO.buscarPorId(cantar.getId());
		AtividadeEclesiastica atividadeBanco2 = atividadeDAO.buscarPorId(tocarGuitarra.getId());

		assertEquals(Status.INATIVO, atividadeBanco1.getStatus());
		assertEquals(Status.ATIVO, atividadeBanco2.getStatus());

	}

}
