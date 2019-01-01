package br.com.churchmanager.service;

import java.math.BigDecimal;
import java.util.List;

import br.com.churchmanager.model.Pessoa;
import br.com.churchmanager.model.dto.Aniversariante;
import br.com.churchmanager.model.dto.Dizimista;
import br.com.churchmanager.model.dto.MembrosPorFaixaEtaria;
import br.com.churchmanager.model.dto.PessoaAtividaEclesiastica;
import br.com.churchmanager.model.filter.PessoaFilter;

public interface PessoaService extends Service<Pessoa>{

	BigDecimal[] quantidadeDeMembros(PessoaFilter filter);

	List<Aniversariante> aniversariantesDoMes(PessoaFilter filter);

	List<Dizimista> listarDizimistas(PessoaFilter filter);

	List<MembrosPorFaixaEtaria> membresiaFaixaEtaria(PessoaFilter filter);

	List<PessoaAtividaEclesiastica> pessoasPorAtividadeEclesiastica(PessoaFilter filter);

	Pessoa findByMatricula(String idPessoa);

}
