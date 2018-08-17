package br.com.churchmanager.bo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import br.com.churchmanager.dao.PessoaDAO;
import br.com.churchmanager.dao.generic.Buscador;
import br.com.churchmanager.exception.NegocioException;
import br.com.churchmanager.model.Pessoa;
import br.com.churchmanager.model.filter.PessoaFilter;
import br.com.churchmanager.model.group.Aniversariante;
import br.com.churchmanager.model.group.Dizimista;
import br.com.churchmanager.model.group.MembrosPorFaixaEtaria;
import br.com.churchmanager.model.group.PessoaAtividaEclesiastica;
import br.com.churchmanager.util.MyLazyDataModel;

public class PessoaBO implements Serializable, Buscador<Pessoa> {

	private static final long serialVersionUID = 1L;

	private static final boolean ORDER_ASC = true;

	@Inject
	PessoaDAO dao;

	public void salvar(Pessoa pessoa) throws NegocioException {
		this.validar(pessoa);
		this.dao.salvar(pessoa);
	}

	public void atualizar(Pessoa pessoa) throws NegocioException {
		this.validar(pessoa);
		this.dao.atualizar(pessoa);
	}

	public void deletar(Pessoa pessoa) {
		this.dao.excluir(pessoa);
	}

	public List<Pessoa> listar() {
		return this.dao.listar(ORDER_ASC);
	}

	public void validar(Pessoa pessoa) throws NegocioException {
		//
	}

	public Pessoa buscarPorId(Long id) {
		return this.dao.buscarPorId(id);
	}

	public Pessoa buscarPorMatricula(String matricula) {
		return this.dao.buscarPorAtributo("matricula", matricula);
	}

	public MyLazyDataModel<Pessoa> filtrar(PessoaFilter pessoaFilter) {
		return this.dao.filtrar(this.dao, pessoaFilter);
	}

	public BigDecimal[] quantidadeDeMembros(PessoaFilter filter) {
		return this.dao.quantidadeDeMembros(filter);
	}

	public List<Aniversariante> aniversariantesDoMes(PessoaFilter filter) {
		return this.dao.aniversariantesDoMes(filter);
	}

	public List<MembrosPorFaixaEtaria> membresiaFaixaEtaria(PessoaFilter filter) {
		return this.dao.membresiaFaixaEtaria(filter);
	}

	public List<PessoaAtividaEclesiastica> pessoasPorAtividadeEclesiastica(PessoaFilter filter) {
		return this.dao.pessoasPorAtividadeEclesiastica(filter);
	}

	public String gerarMatricula(Date dataCadastro) {
		return dao.gerarMatricula(dataCadastro);
	}

	public List<Dizimista> listarDizimistas(PessoaFilter filter) {
		return this.dao.listarDizimistas(filter);
	}
}