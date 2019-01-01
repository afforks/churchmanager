package br.com.churchmanager.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import org.apache.deltaspike.data.api.EntityRepository;

import br.com.churchmanager.jsf.primefaces.LazyDataModel;
import br.com.churchmanager.model.Pessoa;
import br.com.churchmanager.model.dto.Aniversariante;
import br.com.churchmanager.model.dto.Dizimista;
import br.com.churchmanager.model.dto.MembrosPorFaixaEtaria;
import br.com.churchmanager.model.dto.PessoaAtividaEclesiastica;
import br.com.churchmanager.model.filter.Filter;
import br.com.churchmanager.model.filter.PessoaFilter;
import br.com.churchmanager.repository.PessoaRepository;
import br.com.churchmanager.service.PessoaService;

public class PessoaServiceImpl implements PessoaService {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Inject
	private PessoaRepository repository;

	public Pessoa save(Pessoa pessoa) {
		this.validar(pessoa);
		return this.repository.save(pessoa);
	}

	public Pessoa update(Pessoa pessoa) {
		this.validar(pessoa);
		return this.repository.save(pessoa);
	}

	public void delete(Pessoa pessoa) {
		this.repository.remove(pessoa);
	}

	public List<Pessoa> findAll() {
		return this.repository.findAll();
	}

	public void validar(Pessoa pessoa) {
		//
	}

	public Pessoa findBy(Long id) {
		return this.repository.findBy(id);
	}

	public Pessoa buscarPorMatricula(String matricula) {
		return this.repository.buscarPorMatricula(matricula);
	}

	public LazyDataModel<Pessoa> filtrar(PessoaFilter pessoaFilter) {
		// return this.repository.lazyList(this.repository);
		return null;
	}

	public BigDecimal[] quantidadeDeMembros(PessoaFilter filter) {
		return this.repository.quantidadeDeMembros(filter);
	}

	public List<Aniversariante> aniversariantesDoMes(PessoaFilter filter) {
		return this.repository.aniversariantesDoMes(filter);
	}

	public List<MembrosPorFaixaEtaria> membresiaFaixaEtaria(PessoaFilter filter) {
		return this.repository.membresiaFaixaEtaria(filter);
	}

	public List<PessoaAtividaEclesiastica> pessoasPorAtividadeEclesiastica(PessoaFilter filter) {
		return this.repository.pessoasPorAtividadeEclesiastica(filter);
	}

	public String gerarMatricula(Date dataCadastro) {
		return repository.gerarMatricula(dataCadastro);
	}

	public List<Dizimista> listarDizimistas(PessoaFilter filter) {
		return this.repository.findAllDizimistas(filter);
	}

	@Override
	public EntityRepository<Pessoa, Long> getRepository() {
		return repository;
	}

	@Override
	public void updateStatus(Pessoa t) {
		t.alterarStatus();
		update(t);
	}

	@Override
	public LazyDataModel<Pessoa> lazyList(Filter filter) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Pessoa> autoComplete(String value) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Pessoa findByMatricula(String idPessoa) {
		return repository.findByMatricula(idPessoa);
	}
}