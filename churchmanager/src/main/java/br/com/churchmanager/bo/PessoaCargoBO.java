package br.com.churchmanager.bo;

import br.com.churchmanager.dao.PessoaCargoDAO;
import br.com.churchmanager.generic.dao.Buscador;
import br.com.churchmanager.model.PessoaCargo;
import br.com.churchmanager.model.filter.PessoaCargoFilter;
import br.com.churchmanager.util.MyLazyDataModel;

import java.io.Serializable;
import java.util.List;
import javax.inject.Inject;

public class PessoaCargoBO implements Serializable, Buscador<PessoaCargo> {
	private static final long serialVersionUID = 1L;

	private static final boolean ORDER_ASC = true;

	@Inject
	PessoaCargoDAO dao;

	public void salvar(PessoaCargo pc) throws Exception {
		this.validar(pc);
		this.dao.salvar(pc);
	}

	public void atualizar(PessoaCargo pc) throws Exception {
		this.validar(pc);
		this.dao.atualizar(pc);
	}

	public void deletar(PessoaCargo pc) {
		this.dao.excluir(pc);
	}

	public List<PessoaCargo> listar() {
		return this.dao.listar(ORDER_ASC);
	}

	public void validar(PessoaCargo pc) throws Exception {
		if (pc.getPessoa() == null) {
			throw new Exception("É necessário selecionar uma pessoa!");
		} else if (pc.getCargo() == null) {
			throw new Exception("É necessário selecionar um cargo!");
		}
	}

	public PessoaCargo buscarPorId(Serializable id) {
		return (PessoaCargo) this.dao.buscarPorId(id);
	}

	public MyLazyDataModel<PessoaCargo> filtrar(PessoaCargoFilter pcFilter) {
		return this.dao.filtrar(this.dao, pcFilter);
	}
}