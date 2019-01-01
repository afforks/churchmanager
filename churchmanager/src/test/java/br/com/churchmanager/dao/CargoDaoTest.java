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

import br.com.churchmanager.builder.CargoBuilder;
import br.com.churchmanager.exception.ViolacaoDeRestricaoException;
import br.com.churchmanager.model.Cargo;
import br.com.churchmanager.model.Status;
import br.com.churchmanager.repository.CargoRepository;
import br.com.churchmanager.util.JPAUtil;

public class CargoDaoTest {

	private static final boolean DISTINCT_TRUE = true;
	private static final boolean ASC = true;

	private EntityManager entityManager;
	private CargoRepository cargoRepository;
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
		cargoRepository = new CargoRepository(entityManager);
		cargo = novaCargo();
	}

	@After
	public void tearDown() {
		entityManager.getTransaction().rollback();
		entityManager.close();
		cargoRepository = null;
		cargo = null;
	}

	@Test
	public void deveCadastrarCargo() {

		long qtdRegistros = cargoRepository.contagem("id", null, null, DISTINCT_TRUE);
		cargoRepository.save(cargo);
		long totalAtualRegistros = cargoRepository.contagem("id", null, null, DISTINCT_TRUE);

		Cargo cargoDoBanco = cargoRepository.findBy(cargo.getId());

		assertEquals(qtdRegistros + 1, totalAtualRegistros);
		assertEquals("id", cargo.getId(), cargoDoBanco.getId());
		assertEquals("nome", cargo.getNome(), cargoDoBanco.getNome());
		assertEquals("status", cargo.getStatus(), cargoDoBanco.getStatus());
		assertEquals("descricao", cargo.getDescricao(), cargoDoBanco.getDescricao());

	}

	@Test
	public void deveEditarCargo() {
		cargoRepository.save(cargo);

		cargo.setNome("Vice-presidente");
		cargo.setDescricao("Uma nova descrição");

		cargoRepository.save(cargo);

		Cargo cargoDoBanco = cargoRepository.buscarPorAtributo("nome", cargo.getNome());

		assertEquals("id", cargo.getId(), cargoDoBanco.getId());
		assertEquals("nome", cargo.getNome(), cargoDoBanco.getNome());
		assertEquals("status", cargo.getStatus(), cargoDoBanco.getStatus());
		assertEquals("descricao", cargo.getDescricao(), cargoDoBanco.getDescricao());

	}

	@Test
	public void deveRemoverCargo() {
		cargoRepository.save(cargo);
		long qtdRegistros = cargoRepository.contagem("id", null, null, DISTINCT_TRUE);
		cargoRepository.remove(cargo);
		long totalAtualRegistros = cargoRepository.contagem("id", null, null, DISTINCT_TRUE);
		cargo = cargoRepository.findBy(cargo.getId());
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

		cargoRepository.save(presidente);
		cargoRepository.save(vicePresidente);
		cargoRepository.save(tesoureiro);
		cargoRepository.save(Secretario);

		List<Cargo> cargos = cargoRepository.findAll(ASC, "nome");
		int tamanhoDaLista = cargos.size();

		assertEquals(4, tamanhoDaLista);
		assertEquals(presidente.getNome(), cargos.get(0).getNome());
		assertEquals(Secretario.getNome(), cargos.get(1).getNome());
		assertEquals(tesoureiro.getNome(), cargos.get(2).getNome());
		assertEquals(vicePresidente.getNome(), cargos.get(3).getNome());

	}

	@Test
	public void deveBuscarCargoPorIdentificador() {

		cargoRepository.save(cargo);
		Cargo cargoDoBanco = cargoRepository.findBy(cargo.getId());

		assertEquals("id", cargo.getId(), cargoDoBanco.getId());
		assertEquals("nome", cargo.getNome(), cargoDoBanco.getNome());
		assertEquals("status", cargo.getStatus(), cargoDoBanco.getStatus());
		assertEquals("descricao", cargo.getDescricao(), cargoDoBanco.getDescricao());

	}

	@Test
	public void deveEncontrarCargoPorNome() {

		cargoRepository.save(cargo);
		Cargo cargoDoBanco = cargoRepository.buscarPorAtributo("nome", "Presidente");

		assertEquals("id", cargo.getId(), cargoDoBanco.getId());
		assertEquals("nome", cargo.getNome(), cargoDoBanco.getNome());
		assertEquals("status", cargo.getStatus(), cargoDoBanco.getStatus());
		assertEquals("descricao", cargo.getDescricao(), cargoDoBanco.getDescricao());
	}

	@Test
	public void deveRetornarNuloSeNaoEncontrarCargoPorNome() {
		Cargo cargoDoBanco = cargoRepository.buscarPorAtributo("nome", "Vice-presidente");
		assertNull(cargoDoBanco);
	}

	@Test
	public void naoDeveTerNomeDuplicado() {
		expectedException.expect(ViolacaoDeRestricaoException.class);

		Cargo cantar1 = new CargoBuilder().comNome("Vice-presidente").comDescricao("Descrição do cargo").ativo()
				.build();
		Cargo cantar2 = new CargoBuilder().comNome("Vice-presidente").comDescricao("Descrição do cargo").ativo()
				.build();

		cargoRepository.save(cantar1);
		cargoRepository.save(cantar2);

	}

	@Test
	public void deveAlterarStatus() {
		Cargo cantar = new CargoBuilder().comNome("Vice-presidente").comDescricao("Descrição do cargo").ativo().build();
		Cargo tocarGuitarra = new CargoBuilder().comNome("Presidente").comDescricao("Descrição do cargo").inativo()
				.build();

		cargoRepository.save(cantar);
		cargoRepository.save(tocarGuitarra);

		cargoRepository.saveStatus(cantar);
		cargoRepository.saveStatus(tocarGuitarra);

		Cargo cargoBanco1 = cargoRepository.findBy(cantar.getId());
		Cargo cargoBanco2 = cargoRepository.findBy(tocarGuitarra.getId());

		assertEquals(Status.INATIVO, cargoBanco1.getStatus());
		assertEquals(Status.ATIVO, cargoBanco2.getStatus());

	}

}
