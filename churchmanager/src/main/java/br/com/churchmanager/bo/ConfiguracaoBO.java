package br.com.churchmanager.bo;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import br.com.churchmanager.dao.ConfiguracaoDAO;
import br.com.churchmanager.exception.NegocioException;
import br.com.churchmanager.model.Configuracao;
import br.com.churchmanager.model.filter.ConfiguracaoFilter;
import br.com.churchmanager.util.MyLazyDataModel;

public class ConfiguracaoBO implements Serializable {

	private static final long serialVersionUID = 1275984634234444L;

	private static final boolean ORDER_ASC = true;

	@Inject
	private ConfiguracaoDAO dao;

	public void salvar(Configuracao configuracao) throws NegocioException {
		this.validar(configuracao);
		this.dao.salvar(configuracao);
	}

	public void atualizar(Configuracao configuracao) throws NegocioException {
		this.validar(configuracao);
		this.dao.atualizar(configuracao);
	}

	public void deletar(Configuracao configuracao) {
		this.dao.excluir(configuracao);
	}

	public List<Configuracao> listar() {
		return this.dao.listar(ORDER_ASC);
	}

	public void validar(Configuracao configuracao) throws NegocioException {
		//
	}

	public Configuracao buscarPorId(Long id) {
		return this.dao.buscarPorId(id);
	}

	public MyLazyDataModel<Configuracao> filtrar(ConfiguracaoFilter configuracaoFilter) {
		return this.dao.filtrar(this.dao, configuracaoFilter);
	}

	public Configuracao buscarUltimaConfiguracao() {
		return dao.buscarUltimaConfiguracao();
	}

}
