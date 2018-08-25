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

import br.com.churchmanager.builder.CargoBuilder;
import br.com.churchmanager.exception.ViolacaoDeRestricaoException;
import br.com.churchmanager.model.Cargo;
import br.com.churchmanager.model.Status;
import br.com.churchmanager.util.JPAUtil;

public class CargoDaoTest {

	private static final boolean DISTINCT_TRUE = true;
	private static final boolean ASC = true;

	private EntityManager entityManager;
	private CargoDAO cargoDAO;
	private Cargo cargo;

	@Rule
	public ExpectedException expectedException = ExpectedException.none();

	private Cargo novaCargo() {
		Cargo cargo = new CargoBuilder().comNome("Presidente").comDescricao("Descrição do cargo").ativo().build();
		return cargo;
	}

	@Before
	public void setUp() {
		entityManager = new JPAUtil().getEntityManager();
		entityManager.getTransaction().begin();
		cargoDAO = new CargoDAO(entityManager);
		cargo = novaCargo();
	}

	@After
	public void tearDown() {
		entityManager.getTransaction().rollback();
		entityManager.close();
		cargoDAO = null;
		cargo = null;
	}

	@Test
	public void deveCadastrarCargo() {

		long qtdRegistros = cargoDAO.contagem("id", null, null, DISTINCT_TRUE);
		cargoDAO.salvar(cargo);
		long totalAtualRegistros = cargoDAO.contagem("id", null, null, DISTINCT_TRUE);

		Cargo cargoDoBanco = cargoDAO.buscarPorId(cargo.getId());

		assertEquals(qtdRegistros + 1, totalAtualRegistros);
		assertEquals("id", cargo.getId(), cargoDoBanco.getId());
		assertEquals("nome", cargo.getNome(), cargoDoBanco.getNome());
		assertEquals("status", cargo.getStatus(), cargoDoBanco.getStatus());
		assertEquals("descricao", cargo.getDescricao(), cargoDoBanco.getDescricao());

	}

	@Test
	public void deveEditarCargo() {
		cargoDAO.salvar(cargo);

		cargo.setNome("Vice-presidente");
		cargo.setDescricao("Uma nova descrição");

		cargoDAO.atualizar(cargo);

		Cargo cargoDoBanco = cargoDAO.buscarPorAtributo("nome", cargo.getNome());

		assertEquals("id", cargo.getId(), cargoDoBanco.getId());
		assertEquals("nome", cargo.getNome(), cargoDoBanco.getNome());
		assertEquals("status", cargo.getStatus(), cargoDoBanco.getStatus());
		assertEquals("descricao", cargo.getDescricao(), cargoDoBanco.getDescricao());

	}

	@Test
	public void deveRemoverCargo() {
		cargoDAO.salvar(cargo);
		long qtdRegistros = cargoDAO.contagem("id", null, null, DISTINCT_TRUE);
		cargoDAO.excluir(cargo);
		long totalAtualRegistros = cargoDAO.contagem("id", null, null, DISTINCT_TRUE);
		cargo = cargoDAO.buscarPorId(cargo.getId());
		assertNull(cargo);
		assertEquals(qtdRegistros - 1, totalAtualRegistros);
	}

	@Test
	public void deveListarCargosOrdenandoPorNome() {

		Cargo presidente = new CargoBuilder().comNome("Presidente").comDescricao("Descrição do cargo").ativo().build();
		Cargo vicePresidente = new CargoBuilder().comNome("Vice-presidente").comDescricao("Descrição do cargo").ativo()
				.build();
		Cargo Secretario = new CargoBuilder().comNome("Secretário").comDescricao("Descrição do cargo").ativo().build();
		Cargo tesoureiro = new CargoBuilder().comNome("Tesoureiro").comDescricao("Descrição do cargo").ativo().build();

		cargoDAO.salvar(presidente);
		cargoDAO.salvar(vicePresidente);
		cargoDAO.salvar(tesoureiro);
		cargoDAO.salvar(Secretario);

		List<Cargo> cargos = cargoDAO.listar(ASC, "nome");
		int tamanhoDaLista = cargos.size();

		assertEquals(4, tamanhoDaLista);
		assertEquals(presidente.getNome(), cargos.get(0).getNome());
		assertEquals(Secretario.getNome(), cargos.get(1).getNome());
		assertEquals(tesoureiro.getNome(), cargos.get(2).getNome());
		assertEquals(vicePresidente.getNome(), cargos.get(3).getNome());

	}

	@Test
	public void deveBuscarCargoPorIdentificador() {

		cargoDAO.salvar(cargo);
		Cargo cargoDoBanco = cargoDAO.buscarPorId(cargo.getId());

		assertEquals("id", cargo.getId(), cargoDoBanco.getId());
		assertEquals("nome", cargo.getNome(), cargoDoBanco.getNome());
		assertEquals("status", cargo.getStatus(), cargoDoBanco.getStatus());
		assertEquals("descricao", cargo.getDescricao(), cargoDoBanco.getDescricao());

	}

	@Test
	public void deveEncontrarCargoPorNome() {

		cargoDAO.salvar(cargo);
		Cargo cargoDoBanco = cargoDAO.buscarPorAtributo("nome", "Presidente");

		assertEquals("id", cargo.getId(), cargoDoBanco.getId());
		assertEquals("nome", cargo.getNome(), cargoDoBanco.getNome());
		assertEquals("status", cargo.getStatus(), cargoDoBanco.getStatus());
		assertEquals("descricao", cargo.getDescricao(), cargoDoBanco.getDescricao());
	}

	@Test
	public void deveRetornarNuloSeNaoEncontrarCargoPorNome() {
		Cargo cargoDoBanco = cargoDAO.buscarPorAtributo("nome", "Vice-presidente");
		assertNull(cargoDoBanco);
	}

	@Test
	public void naoDeveTerNomeDuplicado() {
		expectedException.expect(ViolacaoDeRestricaoException.class);

		Cargo cantar1 = new CargoBuilder().comNome("Vice-presidente").comDescricao("Descrição do cargo").ativo()
				.build();
		Cargo cantar2 = new CargoBuilder().comNome("Vice-presidente").comDescricao("Descrição do cargo").ativo()
				.build();

		cargoDAO.salvar(cantar1);
		cargoDAO.salvar(cantar2);

	}

	@Test
	public void deveAlterarStatus() {
		Cargo cantar = new CargoBuilder().comNome("Vice-presidente").comDescricao("Descrição do cargo").ativo().build();
		Cargo tocarGuitarra = new CargoBuilder().comNome("Presidente").comDescricao("Descrição do cargo").inativo()
				.build();

		cargoDAO.salvar(cantar);
		cargoDAO.salvar(tocarGuitarra);

		cargoDAO.atualizarStatus(cantar);
		cargoDAO.atualizarStatus(tocarGuitarra);

		Cargo cargoBanco1 = cargoDAO.buscarPorId(cantar.getId());
		Cargo cargoBanco2 = cargoDAO.buscarPorId(tocarGuitarra.getId());

		assertEquals(Status.INATIVO, cargoBanco1.getStatus());
		assertEquals(Status.ATIVO, cargoBanco2.getStatus());

	}

}
