package br.com.churchmanager.service;

import br.com.churchmanager.model.Configuracao;

public interface ConfiguaracaoService extends Service<Configuracao>{

	Configuracao buscarUltimaConfiguracao();

}
