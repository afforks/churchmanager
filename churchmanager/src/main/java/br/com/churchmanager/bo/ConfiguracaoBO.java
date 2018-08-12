package br.com.churchmanager.bo;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import br.com.churchmanager.dao.ConfiguracaoDAO;
import br.com.churchmanager.exception.DadosException;
import br.com.churchmanager.exception.NegocioException;
import br.com.churchmanager.exception.ViolacaoDeRestricaoException;
import br.com.churchmanager.model.Configuracao;
import br.com.churchmanager.model.filter.ConfiguracaoFilter;
import br.com.churchmanager.util.MyLazyDataModel;

public class ConfiguracaoBO implements Serializable {

	private static final long serialVersionUID = 1275984634234444L;

	private static final boolean ORDER_ASC = true;

	@Inject
	private ConfiguracaoDAO dao;

	public void salvar(Configuracao Configuracao)
			throws NegocioException, ViolacaoDeRestricaoException, DadosException {
		this.validar(Configuracao);
		this.dao.salvar(Configuracao);
	}

	public void atualizar(Configuracao Configuracao)
			throws NegocioException, ViolacaoDeRestricaoException, DadosException {
		this.validar(Configuracao);
		this.dao.atualizar(Configuracao);
	}

	public void deletar(Configuracao Configuracao) {
		this.dao.excluir(Configuracao);
	}

	public List<Configuracao> listar() {
		return this.dao.listar(ORDER_ASC);
	}

	public void validar(Configuracao Configuracao) throws NegocioException {
	}

	public Configuracao buscarPorId(Long id) {
		return (Configuracao) this.dao.buscarPorId(id);
	}

	public MyLazyDataModel<Configuracao> filtrar(ConfiguracaoFilter ConfiguracaoFilter) {
		return this.dao.filtrar(this.dao, ConfiguracaoFilter);
	}

	public Configuracao buscarUltimaConfiguracao() {
		return dao.buscarUltimaConfiguracao();
	}

}
