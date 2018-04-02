package br.com.churchmanager.bo;

import br.com.churchmanager.dao.DiretoriaDAO;
import br.com.churchmanager.generic.dao.Buscador;
import br.com.churchmanager.model.Diretoria;
import br.com.churchmanager.model.filter.DiretoriaFilter;
import br.com.churchmanager.util.MyLazyDataModel;

import java.io.Serializable;
import java.util.List;
import javax.inject.Inject;

public class DiretoriaBO implements Serializable, Buscador<Diretoria> {
	private static final long serialVersionUID = 1L;
	@Inject
	DiretoriaDAO dao;

	public void salvar(Diretoria evento) {
		this.validar(evento);
		this.dao.salvar(evento);
	}

	public void atualizar(Diretoria evento) {
		this.validar(evento);
		this.dao.atualizar(evento);
	}

	public void deletar(Diretoria evento) {
		this.dao.excluir(evento);
	}

	public List<Diretoria> listar() {
		return this.dao.listar(true, new String[0]);
	}

	public void validar(Diretoria evento) {
	}

	public Diretoria buscarPorId(Serializable id) {
		return (Diretoria) this.dao.buscarPorId(id);
	}

	public MyLazyDataModel<Diretoria> filtrar(DiretoriaFilter eventoFilter) {
		return this.dao.filtrar(this.dao, eventoFilter);
	}
}