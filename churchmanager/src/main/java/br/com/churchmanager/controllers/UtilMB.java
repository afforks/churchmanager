package br.com.churchmanager.controllers;

import br.com.churchmanager.model.Estado;
import br.com.churchmanager.model.EstadoCivil;
import br.com.churchmanager.model.EstadoPatrimonio;
import br.com.churchmanager.model.FormaMovimentacao;
import br.com.churchmanager.model.PerfilEscolaridade;
import br.com.churchmanager.model.Sexo;
import br.com.churchmanager.model.Situacao;
import br.com.churchmanager.model.Status;
import br.com.churchmanager.model.StatusMovimentacao;
import br.com.churchmanager.model.TipoMovimentacao;
import br.com.churchmanager.util.DataUtil;
import br.com.churchmanager.util.Meses;

import java.io.Serializable;
import java.util.List;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

@Named
@ViewScoped
public class UtilMB implements Serializable {
	private static final long serialVersionUID = 9384579348751L;

	public Status[] listarStatus() {
		return Status.values();
	}

	public Estado[] listarEstados() {
		return Estado.values();
	}

	public EstadoCivil[] listarEstadoCivil() {
		return EstadoCivil.values();
	}

	public PerfilEscolaridade[] listarPerfilEscolaridade() {
		return PerfilEscolaridade.values();
	}

	public StatusMovimentacao[] listarStatusMovimentacao() {
		return StatusMovimentacao.values();
	}

	public TipoMovimentacao[] listarTipoMovimentacao() {
		return TipoMovimentacao.values();
	}

	public Sexo[] listarSexo() {
		return Sexo.values();
	}

	public EstadoPatrimonio[] listarEstadoPatrimonio() {
		return EstadoPatrimonio.values();
	}

	public Situacao[] listarSituacao() {
		return Situacao.values();
	}

	public FormaMovimentacao[] listarFormaMovimentacao() {
		return FormaMovimentacao.values();
	}

	public List<Meses> meses() {
		return DataUtil.getListMeses();
	}

	public List<Integer> anos() {
		return DataUtil.getAnos();
	}

}