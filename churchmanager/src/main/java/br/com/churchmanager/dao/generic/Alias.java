package br.com.churchmanager.dao.generic;

import java.io.Serializable;
import org.hibernate.sql.JoinType;

public class Alias implements Serializable {
	private static final long serialVersionUID = 7992347156870651350L;
	private String atributo;
	private String aliasAtributo;
	private JoinType tipoDeJoin;

	public Alias(String atributo, String aliasAtributo) {
		this.atributo = atributo;
		this.aliasAtributo = aliasAtributo;
		this.tipoDeJoin = null;
	}

	public Alias(String atributo, String aliasAtributo, JoinType tipoDeJoin) {
		this.atributo = atributo;
		this.aliasAtributo = aliasAtributo;
		this.tipoDeJoin = tipoDeJoin;
	}

	public String getAtributo() {
		return this.atributo;
	}

	public void setAtributo(String atributo) {
		this.atributo = atributo;
	}

	public String getAliasAtributo() {
		return this.aliasAtributo;
	}

	public void setAliasAtributo(String aliasAtributo) {
		this.aliasAtributo = aliasAtributo;
	}

	public JoinType getTipoDeJoin() {
		return this.tipoDeJoin;
	}

	public void setTipoDeJoin(JoinType tipoDeJoin) {
		this.tipoDeJoin = tipoDeJoin;
	}
}