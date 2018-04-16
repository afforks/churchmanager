package br.com.churchmanager.bo;

import br.com.churchmanager.dao.DiretoriaDAO;
import br.com.churchmanager.exception.DadosException;
import br.com.churchmanager.exception.NegocioException;
import br.com.churchmanager.exception.ViolacaoDeRestricaoException;
import br.com.churchmanager.generic.dao.Buscador;
import br.com.churchmanager.model.Diretoria;
import br.com.churchmanager.model.filter.DiretoriaFilter;
import br.com.churchmanager.util.MyLazyDataModel;

import java.io.Serializable;
import java.util.List;
import javax.inject.Inject;

public class DiretoriaBO implements Serializable, Buscador<Diretoria> {
	private static final long serialVersionUID = 1L;

	private static final boolean ORDER_ASC = true;

	@Inject
	DiretoriaDAO dao;

	public void salvar(Diretoria evento) throws NegocioException, ViolacaoDeRestricaoException, DadosException {
		this.validar(evento);
		this.dao.salvar(evento);
	}

	public void atualizar(Diretoria evento) throws NegocioException, ViolacaoDeRestricaoException, DadosException {
		this.validar(evento);
		this.dao.atualizar(evento);
	}

	public void deletar(Diretoria evento) {
		this.dao.excluir(evento);
	}

	public List<Diretoria> listar() {
		return this.dao.listar(ORDER_ASC);
	}

	public void validar(Diretoria evento) throws NegocioException {
		if (evento.getPessoaCargos() == null || evento.getPessoaCargos().isEmpty()) {
			throw new NegocioException("É necessário selecionar os membros da diretoria");
		}
	}

	public Diretoria buscarPorId(Serializable id) {
		return (Diretoria) this.dao.buscarPorId(id);
	}

	public MyLazyDataModel<Diretoria> filtrar(DiretoriaFilter eventoFilter) {
		return this.dao.filtrar(this.dao, eventoFilter);
	}
}