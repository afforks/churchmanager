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

import br.com.churchmanager.builder.AtividadeEclesiasticaBuilder;
import br.com.churchmanager.exception.ViolacaoDeRestricaoException;
import br.com.churchmanager.model.AtividadeEclesiastica;
import br.com.churchmanager.model.Status;
import br.com.churchmanager.repository.AtividadeEclesiasticaRepository;
import br.com.churchmanager.util.JPAUtil;

public class AtividadeEclesiasticaDaoTest {

	private static final boolean DISTINCT_TRUE = true;
	private static final boolean ASC = true;

	private EntityManager entityManager;
	private AtividadeEclesiasticaRepository atividadeRepository;
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
		atividadeRepository = new AtividadeEclesiasticaRepository(entityManager);
		atividade = novaAtividade();
	}

	@After
	public void tearDown() {
		entityManager.getTransaction().rollback();
		entityManager.close();
		atividadeRepository = null;
		atividade = null;
	}

	@Test
	public void deveCadastrarAtividade() {

		long qtdRegistros = atividadeRepository.contagem("id", null, null, DISTINCT_TRUE);
		atividadeRepository.save(atividade);
		long totalAtualRegistros = atividadeRepository.contagem("id", null, null, DISTINCT_TRUE);

		AtividadeEclesiastica atividadeDoBanco = atividadeRepository.findBy(atividade.getId());

		assertEquals(qtdRegistros + 1, totalAtualRegistros);
		assertEquals("id", atividade.getId(), atividadeDoBanco.getId());
		assertEquals("nome", atividade.getNome(), atividadeDoBanco.getNome());
		assertEquals("status", atividade.getStatus(), atividadeDoBanco.getStatus());
		assertEquals("descricao", atividade.getDescricao(), atividadeDoBanco.getDescricao());

	}

	@Test
	public void deveEditarAtividade() {
		atividadeRepository.save(atividade);

		atividade.setNome("Cantar");
		atividade.setDescricao("Uma nova descrição");

		atividadeRepository.save(atividade);

		AtividadeEclesiastica atividadeDoBanco = atividadeRepository.buscarPorAtributo("nome", atividade.getNome());

		assertEquals("id", atividade.getId(), atividadeDoBanco.getId());
		assertEquals("nome", atividade.getNome(), atividadeDoBanco.getNome());
		assertEquals("status", atividade.getStatus(), atividadeDoBanco.getStatus());
		assertEquals("descricao", atividade.getDescricao(), atividadeDoBanco.getDescricao());

	}

	@Test
	public void deveRemoverAtividade() {
		atividadeRepository.save(atividade);
		long qtdRegistros = atividadeRepository.contagem("id", null, null, DISTINCT_TRUE);
		atividadeRepository.remove(atividade);
		long totalAtualRegistros = atividadeRepository.contagem("id", null, null, DISTINCT_TRUE);
		atividade = atividadeRepository.findBy(atividade.getId());
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

		atividadeRepository.save(tocarGuitarra);
		atividadeRepository.save(cantar);
		atividadeRepository.save(darAula);
		atividadeRepository.save(recepcionarVisitantes);

		List<AtividadeEclesiastica> atividades = atividadeRepository.findAll(ASC, "nome");
		int tamanhoDaLista = atividades.size();

		assertEquals(4, tamanhoDaLista);
		assertEquals(cantar.getNome(), atividades.get(0).getNome());
		assertEquals(darAula.getNome(), atividades.get(1).getNome());
		assertEquals(recepcionarVisitantes.getNome(), atividades.get(2).getNome());
		assertEquals(tocarGuitarra.getNome(), atividades.get(3).getNome());

	}

	@Test
	public void deveBuscarAtividadePorIdentificador() {

		atividadeRepository.save(atividade);
		AtividadeEclesiastica atividadeDoBanco = atividadeRepository.findBy(atividade.getId());

		assertEquals("id", atividade.getId(), atividadeDoBanco.getId());
		assertEquals("nome", atividade.getNome(), atividadeDoBanco.getNome());
		assertEquals("status", atividade.getStatus(), atividadeDoBanco.getStatus());
		assertEquals("descricao", atividade.getDescricao(), atividadeDoBanco.getDescricao());

	}

	@Test
	public void deveEncontrarAtividadePorNome() {

		atividadeRepository.save(atividade);
		AtividadeEclesiastica atividadeDoBanco = atividadeRepository.buscarPorAtributo("nome", "Tocar guitarra");

		assertEquals("id", atividade.getId(), atividadeDoBanco.getId());
		assertEquals("nome", atividade.getNome(), atividadeDoBanco.getNome());
		assertEquals("status", atividade.getStatus(), atividadeDoBanco.getStatus());
		assertEquals("descricao", atividade.getDescricao(), atividadeDoBanco.getDescricao());
	}

	@Test
	public void deveRetornarNuloSeNaoEncontrarAtividadePorNome() {
		AtividadeEclesiastica atividadeDoBanco = atividadeRepository.buscarPorAtributo("nome", "Cantar");
		assertNull(atividadeDoBanco);
	}

	@Test
	public void naoDeveTerNomeDuplicado() {
		expectedException.expect(ViolacaoDeRestricaoException.class);

		AtividadeEclesiastica cantar1 = new AtividadeEclesiasticaBuilder().comNome("Cantar")
				.comDescricao("Descrição da atividade").ativo().build();
		AtividadeEclesiastica cantar2 = new AtividadeEclesiasticaBuilder().comNome("Cantar")
				.comDescricao("Descrição da atividade").ativo().build();

		atividadeRepository.save(cantar1);
		atividadeRepository.save(cantar2);

	}

	@Test
	public void deveAlterarStatus() {
		AtividadeEclesiastica cantar = new AtividadeEclesiasticaBuilder().comNome("Cantar")
				.comDescricao("Descrição da atividade").ativo().build();
		AtividadeEclesiastica tocarGuitarra = new AtividadeEclesiasticaBuilder().comNome("Tocar Guitarra")
				.comDescricao("Descrição da atividade").inativo().build();

		atividadeRepository.save(cantar);
		atividadeRepository.save(tocarGuitarra);

		atividadeRepository.saveStatus(cantar);
		atividadeRepository.saveStatus(tocarGuitarra);

		AtividadeEclesiastica atividadeBanco1 = atividadeRepository.findBy(cantar.getId());
		AtividadeEclesiastica atividadeBanco2 = atividadeRepository.findBy(tocarGuitarra.getId());

		assertEquals(Status.INATIVO, atividadeBanco1.getStatus());
		assertEquals(Status.ATIVO, atividadeBanco2.getStatus());

	}

}
